package com.series.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.series.dto.UserDto;
import com.series.exception.ResourceNotFoundException;
import com.series.model.User;
import com.series.repository.UserRepository;
import com.series.service.RegistrationService;
import com.series.service.UserService;


@RestController
@RequestMapping
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	RegistrationService registrationService;
	
	@GetMapping("/users")
	public List<UserDto> getAllUsers() {
		
		return userService.getAllUsers();
	}
	
//	TODO return value???
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody UserDto userDto) {

// TODO Move .registerNewUser method call into try block
// TODO Define exception
//		User user = null;
//		try {
//			user = registrationService.registerNewUser(userDto);
//		} catch {
//			throw exception
//		}
		
//		TODO I'll probably move this user creation method into UserService 
		User user = registrationService.registerNewUser(userDto);
		
//		TODO User or UserDto
		return user;
	}
	
	@GetMapping("/users/{id}")
	public UserDto getUserById(@PathVariable(value = "id") Long userId) {

//		TODO catch exception
		return userService.findById(userId);
	}
	
	@PutMapping("/users/{id}")
	public UserDto updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody UserDto userDto) {
		
//		TODO catch exception
		return userService.update(userId, userDto);		
	}
}
