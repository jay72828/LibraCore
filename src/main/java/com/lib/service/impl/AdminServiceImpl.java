package com.lib.service.impl;

import com.lib.mapper.UserMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lib.dto.request.AdminLoginReq;
import com.lib.dto.request.AdminRequest;
import com.lib.dto.response.AdminResponse;
import com.lib.dto.response.AuthResponse;
import com.lib.dto.response.UserResponse;
import com.lib.entiity.Admin;
import com.lib.entiity.User;
import com.lib.exception.BadRequestException;
import com.lib.exception.ResourceNotFoundException;
import com.lib.mapper.AdminMapper;
import com.lib.repository.AdminRepository;
import com.lib.repository.UserRepository;
import com.lib.security.JwtUtil;
import com.lib.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	private final UserDetailsService userDetailsService;
	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	private final AdminRepository adminRepository;
	private final AdminMapper adminMapper;
	private final UserRepository userRepository;

	public AdminServiceImpl(
			PasswordEncoder passwordEncoder,
			UserDetailsService userDetailsService,
			AuthenticationManager authenticationManager,
			JwtUtil jwtUtil, AdminRepository adminRepository,
			AdminMapper adminMapper,
			UserRepository userRepository, 
			UserMapper userMapper) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userDetailsService = userDetailsService;
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
		this.adminRepository = adminRepository;
		this.adminMapper = adminMapper;
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Override
	public AuthResponse login(AdminLoginReq adminLoginReq) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				adminLoginReq.getEmail(), adminLoginReq.getPassword());
		authenticationManager.authenticate(authenticationToken);
		UserDetails userDetails = userDetailsService.loadUserByUsername(adminLoginReq.getEmail());
		String token = jwtUtil.generateToken(userDetails);

		return AuthResponse.builder().token(token).username(userDetails.getUsername()).build();
	}

	@Override
	public AdminResponse createAdmin(AdminRequest adminRequest) {
		Optional<Admin> email = adminRepository.findByEmail(adminRequest.getEmail());
		if (email.isPresent()) {
			throw new BadRequestException("User already exists with this email");
		}

		Admin admin = adminMapper.toEntity(adminRequest);

		admin.setPassword(passwordEncoder.encode(adminRequest.getPassword()));
		Admin save = adminRepository.save(admin);
		return adminMapper.toResponse(save);
	}

	@Override
	public List<UserResponse> findAllUsers() {
		List<User> all = userRepository.findAll();
		return userMapper.toResponse(all);
	}

	@Override
	public void deleteById(Long userId) {
		userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
		LocalDateTime plusHours = LocalDateTime.now().plusHours(24);
		if(LocalDateTime.now().isAfter(plusHours)) {
			userRepository.deleteById(userId);
		}
	}
}