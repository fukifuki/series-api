package com.series.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.series.config.JwtTokenUtil;
import com.series.dto.Auth;
import com.series.dto.AuthDetails;
import com.series.dto.UserCredentials;
import com.series.dto.UserDto;
import com.series.service.UserService;

@RestController
@RequestMapping
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> generateToken(@RequestBody UserCredentials credentials) throws AuthenticationException {
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDto user = userService.findByUsername(credentials.getUsername());
		String token = jwtTokenUtil.generateToken(user);
		return ResponseEntity.ok(new Auth(user.getId(), token));
	}
	
//	TODO get current user
	@GetMapping("/me")
	public UserDto getCurrentUser(Principal principal) {
		UserDto currentUser = userService.findByUsername(principal.getName());
		
		return currentUser;
	}
}
