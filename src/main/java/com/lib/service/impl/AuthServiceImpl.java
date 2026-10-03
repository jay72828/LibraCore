package com.lib.service.impl;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lib.dto.request.LoginRequest;
import com.lib.dto.request.UserRequest;
import com.lib.dto.response.AuthResponse;
import com.lib.dto.response.UserResponse;
import com.lib.entiity.User;
import com.lib.exception.BadRequestException;
import com.lib.mapper.UserMapper;
import com.lib.repository.UserRepository;
import com.lib.security.JwtUtil;
import com.lib.service.AuthService;


@Service
//@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private UserRepository userRepository;
	private AuthenticationManager authenticationManager;
	private UserDetailsService userDetailsService;
	private JwtUtil jwtUtil;
	private PasswordEncoder passwordEncoder;
	private final UserMapper userMapper;

   public AuthServiceImpl(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtUtil jwtUtil,
            PasswordEncoder passwordEncoder,
            UserMapper userMapper) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }	


	@Override
	public AuthResponse login(LoginRequest loginRequest) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
		authenticationManager.authenticate(authenticationToken);
		UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
		String token = jwtUtil.generateToken(userDetails);
		
		return AuthResponse.builder()
					.token(token)
					.username(userDetails.getUsername())
					.build();
	}
	
	
	@Override
	public UserResponse createUser(UserRequest userRequest){
		Optional<User> email = userRepository.findByEmail(userRequest.getEmail());
		  if (email.isPresent()) {
		        throw new BadRequestException("User already exists with this email");
		    }
            
		  	User response = userMapper.toEntity(userRequest);
		  	response.setPassword(passwordEncoder.encode(response.getPassword()));
            User save = userRepository.save(response);
		return userMapper.toResponse(save);
	}
}
