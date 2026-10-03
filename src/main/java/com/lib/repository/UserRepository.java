package com.lib.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lib.entiity.User;


public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
}
