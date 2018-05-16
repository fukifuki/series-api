package com.series.service;

import org.springframework.stereotype.Service;

import com.series.dto.UserDto;
import com.series.model.User;


public interface RegistrationService {
	
	User registerNewUser(UserDto userDto);

}
