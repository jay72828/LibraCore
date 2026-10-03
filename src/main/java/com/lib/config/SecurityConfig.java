package com.lib.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.lib.security.JwtAuthenticationEntryPoint;
import com.lib.security.JwtAuthenticationFilter;

@Component
public class SecurityConfig {

	private final JwtAuthenticationFilter filter;
	private final JwtAuthenticationEntryPoint entryPoint;
	
	public SecurityConfig(JwtAuthenticationFilter filter, JwtAuthenticationEntryPoint entryPoint) {
		super();
		this.filter = filter;
		this.entryPoint = entryPoint;
	}

	@Bean SecurityFilterChain securityFilterChain(HttpSecurity http) {
		http.csrf(csrf -> csrf.disable())
			.cors(cors -> cors.disable())
			.authorizeHttpRequests(request -> request
							.requestMatchers(
									"/v3/api-docs/**",
									"/swagger-ui/**",
									"/swagger-ui.html"
									).permitAll()
							.requestMatchers("/auth/login", "/auth/create-user").permitAll()
							.requestMatchers("/admin/login", "/admin/create-admin").permitAll()
							.requestMatchers("/books/**").authenticated()
							.anyRequest()
							.authenticated())
			.exceptionHandling(exception -> exception.authenticationEntryPoint(entryPoint))
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
