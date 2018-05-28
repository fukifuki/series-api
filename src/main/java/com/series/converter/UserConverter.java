package com.series.converter;

import java.util.List;

import com.series.dto.UserDto;
import com.series.model.User;

public interface UserConverter {

	User createFromDto(UserDto dto);
	
	User updateFromDto(User user, UserDto dto);
	
	UserDto createFromEntity(User user);
	
	List<UserDto> createFromEntities(List<User> users);
}
