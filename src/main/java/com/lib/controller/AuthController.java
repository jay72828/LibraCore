package com.lib.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lib.dto.request.LoginRequest;
import com.lib.dto.request.UserRequest;
import com.lib.dto.response.AuthResponse;
import com.lib.dto.response.UserResponse;
import com.lib.service.AuthService;
import com.lib.util.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "User APIs")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody LoginRequest loginRequest) {
		AuthResponse login = authService.login(loginRequest);

		ApiResponse<AuthResponse> apiResponse = new ApiResponse<>(true, "login Successfull ", login);

		return ResponseEntity.ok(apiResponse);
	}

	@PostMapping("/create-user")
	public ResponseEntity<ApiResponse<UserResponse>> register(@Valid @RequestBody UserRequest userRequest) {
		UserResponse user = authService.createUser(userRequest);
		ApiResponse<UserResponse> apiResponse = new ApiResponse<>(true, "registration successfully", user);
		return ResponseEntity.ok(apiResponse);
	}
}
