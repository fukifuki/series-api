package com.series.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.series.dto.AuthDetails;
import com.series.service.AuthService;

@RestController
@RequestMapping("/users/{id}/auth")
public class AuthDetailsController {
	
	@Autowired
	AuthService authService;
	
	@GetMapping
	public AuthDetails getAuthDetails(@PathVariable(value = "id") Long id) {
		
//		create AuthService 
		return authService.get(id);
	}
	
//	TODO @PreAuthorize(ROLE = ADMIN)
	@PutMapping
	public AuthDetails updateAuthDetails(@PathVariable(value = "id") Long id, @Valid @RequestBody AuthDetails details) {
		
		return authService.update(id, details);
	}
}
