package com.series.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.series.dto.UserDto;
import com.series.model.User;

public interface UserService extends UserDetailsService {
	
	List<UserDto> getAllUsers();
	
	UserDto findById(Long userId);
	
	User findByUsername(String username);
	
	UserDto update(Long userId, UserDto userDto);
	
//	TODO find a way to get a logged in user here...
//	User getLoggedInUser();
	
}
