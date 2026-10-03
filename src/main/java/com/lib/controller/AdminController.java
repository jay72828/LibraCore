package com.lib.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lib.dto.request.AdminLoginReq;
import com.lib.dto.request.AdminRequest;
import com.lib.dto.response.AdminResponse;
import com.lib.dto.response.AuthResponse;
import com.lib.dto.response.UserResponse;
import com.lib.service.AdminService;
import com.lib.util.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin APIs")
public class AdminController {

	private final AdminService adminService;
	
	public AdminController(AdminService adminService) {
		super();
		this.adminService = adminService;
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<AuthResponse>> login(
			@Valid 
			@RequestBody 
			AdminLoginReq adminLoginReq
			) {
		AuthResponse login = adminService.login(adminLoginReq);
		ApiResponse<AuthResponse> apiResponse = 
				new ApiResponse<>(
									true, 
									"SUCCESS",
									login
								);
		return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping("/create-admin")
	public ResponseEntity<ApiResponse<AdminResponse>> createAdmin(
			@RequestBody 
			AdminRequest adminRequest
			) {
		
		AdminResponse admin = adminService.createAdmin(adminRequest);
		ApiResponse<AdminResponse> apiResponse =
				new ApiResponse<>(
									true,
									"SUCCESS",
									admin
								);
		return ResponseEntity.ok(apiResponse);
	}
	
	
	@GetMapping("/all-user")
	public ResponseEntity<ApiResponse<List<UserResponse>>> findAllUsers() {
		List<UserResponse> allUsers = adminService.findAllUsers();
		ApiResponse<List<UserResponse>> apiResponse = 
				new ApiResponse<>(
								true,
								"SUCCESS",
								allUsers
				);
		return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping("/delete-user/{userId}")
	public ResponseEntity<ApiResponse<String>> deleteById(@PathVariable Long userId) {
		adminService.deleteById(userId);
		ApiResponse<String> apiResponse = new ApiResponse<>(
					true,
					"SUCCESS",
					"Your id will be deleted into 24 hours"
				);
		return ResponseEntity.ok(apiResponse);
	}
}
