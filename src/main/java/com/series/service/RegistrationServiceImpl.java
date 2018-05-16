package com.series.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.series.dto.UserDto;
import com.series.model.User;
import com.series.repository.RoleRepository;
import com.series.repository.UserRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {

//	needed for checking if user already exist in DB
	@Autowired
	private UserRepository userRepository;
	
//	needed for getting a proper role from DB by its name
//	that probably isn't the way it should be done...
//	TODO pitaj Igora za ovo...
	@Autowired
	private RoleRepository roleRepository;
	
// TODO see what exactly @Transactional annotation means and if it is needed here
// TODO also throw exception if user with a given name already exists
// TODO incorporate other validations here or in controller
// TODO interface as a parameter instead of a specific implementation
	@Override
	public User registerNewUser(UserDto userDto) {
		
		if (usernameExists(userDto.getUsername())) {
//			throw exception here
		}
		
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(encodePassword(userDto.getPassword()));
		user.setEmail(userDto.getEmail());
		user.setEnabled(true);
		user.setRole(roleRepository.findByName("ROLE_USER"));
	
		return userRepository.save(user);
	}
	
//	TODO probably still too much coupling here
	private String encodePassword(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}
	
	private boolean usernameExists(String username) {
		if (userRepository.findByUsername(username) != null) {
			return true;
		}
		return false;
	}
}
