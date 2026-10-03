package com.lib.dto.response;

import com.lib.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponse {

	private String username;
	private String whereHouseId;
	private String email;
	private Role role;
	private String city;
	private String createdAt;
	
}
