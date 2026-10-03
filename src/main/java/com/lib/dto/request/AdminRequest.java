package com.lib.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequest {

	@NotBlank(message = "Username is required")
	@Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
	private String username;


	@NotBlank(message = "Password is required")
	@Size(min = 6, max = 16, message = "Password must be between 6 and 16 characters")
	private String password;


	@NotBlank(message = "Warehouse ID is required")
	private String whereHouseId;


	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "City is required")
	@Size(min = 2, max = 50, message = "City must be between 2 and 50 characters")
	private String city;
}
