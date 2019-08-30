package com.series.converter;

import com.series.dto.AuthDetails;
import com.series.model.User;

public interface AuthDetailsConverter {
	
	AuthDetails createFromEntity(User user);
	
	User updateFromDto(User user, AuthDetails details);
}
