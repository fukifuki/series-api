package com.series.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.series.model.User;

public interface UserService extends UserDetailsService {
	
	User findByUsername(String username);
	
	User getLoggedInUser();

}
