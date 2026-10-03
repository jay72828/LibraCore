package com.lib.dto.response;

import com.lib.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

	private String name;
	private String email;
	private String phone;
	private Role role;
	private boolean active;
	private String createdAt;
	private String updatedAt;
}
