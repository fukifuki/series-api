package com.series.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.series.converter.AuthDetailsConverter;
import com.series.dto.AuthDetails;
import com.series.exception.ResourceNotFoundException;
import com.series.model.User;
import com.series.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	AuthDetailsConverter authConverter;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public AuthDetails get(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		
		return authConverter.createFromEntity(user);
	}

	@Override
	public AuthDetails update(Long id, AuthDetails details) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		
		return authConverter.createFromEntity(authConverter.updateFromDto(user, details));	
	}

}
