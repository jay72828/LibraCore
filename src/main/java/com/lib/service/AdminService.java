package com.lib.service;

import java.util.List;

import com.lib.dto.request.AdminLoginReq;
import com.lib.dto.request.AdminRequest;
import com.lib.dto.response.AdminResponse;
import com.lib.dto.response.AuthResponse;
import com.lib.dto.response.UserResponse;

public interface AdminService {

	AuthResponse login(AdminLoginReq adminLoginReq);

	AdminResponse createAdmin(AdminRequest adminRequest);

	List<UserResponse> findAllUsers();

	void deleteById(Long userId);

}
