package com.lib.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.lib.dto.request.AdminRequest;
import com.lib.dto.response.AdminResponse;
import com.lib.entiity.Admin;
import com.lib.enums.Role;

@Component
public class AdminMapper {

	public Admin toEntity(AdminRequest adminRequest) {
		Admin admin = new Admin();
		admin.setUsername(adminRequest.getUsername());
		admin.setPassword(adminRequest.getPassword());
		admin.setEmail(adminRequest.getEmail());
		admin.setCity(adminRequest.getCity());
		admin.setWhereHouseId(adminRequest.getWhereHouseId());
		admin.setRole(Role.ADMIN.toString());
		admin.setCreatedAt(LocalDateTime.now().toString());
		return admin;
	}
	
	public AdminResponse toResponse(Admin admin) {
		AdminResponse response = new AdminResponse();
		response.setUsername(admin.getUsername());
		response.setEmail(admin.getEmail());
		response.setCity(admin.getCity());
		response.setWhereHouseId(admin.getWhereHouseId());
		response.setRole(Role.ADMIN);
		response.setCreatedAt(LocalDateTime.now().toString());
		return response;
	}
}
