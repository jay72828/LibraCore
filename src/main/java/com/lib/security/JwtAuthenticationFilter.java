package com.lib.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	private JwtUtil jwtUtil;
	private UserDetailsService userDetailsService;
	
	public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
		super();
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");
		String token = null;
		String username = null;
		if(header != null && header.startsWith("Bearer ")) {
			token = header.substring(7);
			try {
				username = jwtUtil.getUsernameFromToken(token);
				log.info("jwt username {} ", username);
			}catch (ExpiredJwtException e) {
				log.info("jwt token expired {}", username);
			}catch (JwtException e) {
				log.info("invalid jwt token ");
			}catch (IllegalArgumentException e) {
				log.info("Unable to read jwt token");
			}
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			boolean validation = jwtUtil.getValidation(token, userDetails);
			if(validation) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				log.info("jwt authentication successfull {}", username);
			}
		}
		filterChain.doFilter(request, response);
	}
}
