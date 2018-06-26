package com.series.config;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.series.dto.UserDto;
import com.series.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
		
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser()
				.setSigningKey(JwtAuthenticationFilter.SIGNING_KEY)
				.parseClaimsJws(token)
				.getBody();
	}
	
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	public String generateToken(UserDto user) { 
		Claims claims = Jwts.claims().setSubject(user.getUsername());
//		claims.put("scopes", Arrays.asList(user.getAuthorities()));
		claims.put("role", user.getRole());
		String token = Jwts.builder()
						.setClaims(claims)
//						TODO set issuer!!!!!!!!!!!!!!!!!!
//						.setIssuer("")
						.setIssuedAt(new Date(System.currentTimeMillis()))
						.setExpiration(new Date(System.currentTimeMillis() + JwtAuthenticationFilter.ACCESS_TOKEN_VALIDITY_SECONDS))
						.signWith(SignatureAlgorithm.HS256, JwtAuthenticationFilter.SIGNING_KEY)
						.compact();
		
		return token;
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}
}
