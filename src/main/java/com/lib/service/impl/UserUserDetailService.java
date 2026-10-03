package com.lib.service.impl;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lib.entiity.Admin;
import com.lib.entiity.User;
import com.lib.repository.AdminRepository;
import com.lib.repository.UserRepository;

@Service
public class UserUserDetailService implements UserDetailsService {

	private final UserRepository userRepository;
	private final AdminRepository adminRepository;

	public UserUserDetailService(UserRepository userRepository, AdminRepository adminRepository) {
		super();
		this.userRepository = userRepository;
		this.adminRepository = adminRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
	        throws UsernameNotFoundException {

	    Optional<Admin> admin = adminRepository.findByEmail(username);

	    if (admin.isPresent()) {
	        return admin.get();
	    }

	    Optional<User> user = userRepository.findByEmail(username);

	    if (user.isPresent()) return user.get();
	    throw new UsernameNotFoundException("User not found with email: " + username);
	}

}
