package com.lib.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	private String name;

	@NotBlank(message = "Email is required")
	@Email(message = "Enter a valid email")
	private String email;

	@NotBlank(message = "Password is required")
	@Size(min = 4, max = 16, message = "Password must be between 4 and 16 characters")
	private String password;

	@NotBlank(message = "Phone is required")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Enter a valid 10-digit phone number")
	private String phone;
	

}
