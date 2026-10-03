package com.lib.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lib.dto.request.UserRequest;
import com.lib.dto.response.UserResponse;
import com.lib.entiity.User;
import com.lib.enums.Role;

@Component
public class UserMapper {

	public User toEntity(UserRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());

        user.setRole(Role.USER);
        user.setActive(true);

        user.setCreatedAt(LocalDateTime.now().toString());
        user.setUpdatedAt(LocalDateTime.now().toString());

        return user;
    }


	public UserResponse toResponse(User registerRequest) {
		 UserResponse response = new UserResponse();

		response.setName(registerRequest.getName());
		response.setEmail(registerRequest.getEmail());
		response.setPhone(registerRequest.getPhone());
		response.setCreatedAt(LocalDateTime.now().toString());
		response.setRole(Role.USER);
		response.setUpdatedAt(LocalDateTime.now().toString());
		response.setActive(true);
		return response;
	}
	
	public List<UserResponse> toResponse(List<User> users) {
	    List<UserResponse> responses = new ArrayList<>();
	    users.forEach(user-> responses.add(toResponse(user)));	    
	    return responses;
	}
}
