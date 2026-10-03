package com.lib.service;


import com.lib.dto.request.LoginRequest;
import com.lib.dto.request.UserRequest;
import com.lib.dto.response.AuthResponse;
import com.lib.dto.response.UserResponse;

public interface AuthService {

	AuthResponse login(LoginRequest loginRequest);

	UserResponse createUser(UserRequest userRequest);
}
