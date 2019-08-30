package com.series.converter;

import org.springframework.stereotype.Component;

import com.series.dto.AuthDetails;
import com.series.model.Role;
import com.series.model.User;

@Component
public class AuthDetailsConverterImpl implements AuthDetailsConverter {

	@Override
	public AuthDetails createFromEntity(User user) {
		AuthDetails details = new AuthDetails();
		details.setUsername(user.getUsername());
		details.setRole(user.getRole());
		details.setEnabled(user.isEnabled());
		
		return details;
	}

	@Override
	public User updateFromDto(User user, AuthDetails details) {
		user.setRole(new Role(details.getRole()));
		user.setEnabled(details.isEnabled());
		
		return user;
	}
	
	
	
}
