package com.lib.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${jwt.secret-key}")
	private String secretKey;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	private SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
	}
	
	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parser()
					.verifyWith(getSecretKey())
					.build()
					.parseSignedClaims(token)
					.getPayload();
	}
	
	public String doGenerateToken(String subject, Map<String, Object> claims){
		return Jwts.builder()
					.claims(claims)
					.subject(subject)
					.issuedAt(new Date(System.currentTimeMillis()))
					.expiration(new Date(System.currentTimeMillis() + expiration))
					.signWith(getSecretKey())
					.compact();
	}
	
	public <T>T getClaimsFromToken(String token, Function<Claims, T> function){
		Claims claims = getAllClaimsFromToken(token);
		return function.apply(claims);
	}
	
	public String getUsernameFromToken(String token) {
		return getClaimsFromToken(token, claims -> claims.getSubject());
	}
	
	public Date getTokenFromExpiration(String token) {
		return getClaimsFromToken(token, claims -> claims.getExpiration());
	}
	
	public boolean isTokenValidation(String token) {
		Date date = getTokenFromExpiration(token);
		return date.after(new Date());
	}
	
	public boolean getValidation(String token, UserDetails userDetails) {
		String username = getUsernameFromToken(token);
		return username.equals(userDetails.getUsername()) && isTokenValidation(token);
	}
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(userDetails.getUsername(), claims);
	}
}
