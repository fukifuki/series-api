package com.series.service;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.series.converter.UserConverter;
import com.series.dto.UserDto;
import com.series.exception.ResourceNotFoundException;
import com.series.model.User;
import com.series.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserConverter userConverter;
	
	@Autowired
	private UserRepository userRepository;
	
	private Principal principal;
	
	@Override
//	@Transactional
	public List<UserDto> getAllUsers() {
		
		List<User> users = userRepository.findAll();
		
		return userConverter.createFromEntities(users);
	}

	@Override
	public UserDto findById(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		return userConverter.createFromEntity(user);
	}
	
	@Override
	public UserDto update(Long userId, UserDto userDto) {
		
		User user = userRepository.getOne(userId);
//		throw an exception if there's no user with a given id in db... something along this line:
//				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		User updatedUser = userConverter.updateFromDto(user, userDto);
		
//		TODO return value??? maybe I should save user to db and then convert it into userDto and send DTO back to client
		
		return userConverter.createFromEntity(userRepository.save(updatedUser));
	}
	
	@Override
	public UserDto findByUsername(String username) {
		User user =  userRepository.findByUsername(username);
		
		return userConverter.createFromEntity(user);
	}
	
	@Override
	public void delete(Long userId) {
		
		User user = userRepository.getOne(userId);
		
		userRepository.delete(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		return user;
	}
	
	
//	Should Principal object be passed into the method as an argument instead?
//	@Override
//	public User getLoggedInUser() {
//		return findByUsername(principal.getName());
//	}
}
