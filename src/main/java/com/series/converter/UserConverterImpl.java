package com.series.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.series.dto.UserDto;
import com.series.model.Role;
import com.series.model.User;

@Component
public class UserConverterImpl implements UserConverter {
	
	@Override
	public User createFromDto(UserDto dto) {
		User user = new User();
		
		user.setId(dto.getId());
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getUsername());
		user.setEmail(dto.getUsername());
		user.setEnabled(true);
		Role role = new Role("ROLE_USER");
		user.setRole(role);
		
		return user;
	}

	@Override
	public User updateFromDto(User user, UserDto dto) {
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getUsername());
		user.setEmail(dto.getUsername());
		
		return user;
	}

	@Override
	public UserDto createFromEntity(User user) {
		UserDto dto = new UserDto();
		
		dto.setId(user.getId());
		dto.setUsername(user.getUsername());
		dto.setPassword(user.getPassword());
		dto.setEmail(user.getEmail());
		dto.setRole(user.getRole());
		
		return dto;
	}

	@Override
	public List<UserDto> createFromEntities(List<User> users) {
		List<UserDto> dtos = new ArrayList<UserDto>();
		users.forEach(user -> { 
			dtos.add(createFromEntity(user)); 
		});
		
		return dtos;
	}
	
}
