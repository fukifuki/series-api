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


@RestController
@RequestMapping
public class UserController {
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	RegistrationService registrationService;
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody UserDto userDto) {

// TODO Move .registerNewUser method call into try block
// TODO Define exception
//		User user = null;
		User user = registrationService.registerNewUser(userDto);
//		try {
//			user = registrationService.registerNewUser(userDto);
//		} catch {
//			throw exception
//		}
		
		return user;
	}
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable(value = "id") Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
	}
	
	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		user.setUsername(userDetails.getEmail());
		user.setPassword(userDetails.getPassword());
		user.setEmail(userDetails.getEmail());
		
		User updatedUser = userRepository.save(user);
		return updatedUser;
	}
}
