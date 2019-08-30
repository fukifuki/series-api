package com.series.service;

import com.series.dto.AuthDetails;

public interface AuthService {
	
	AuthDetails get(Long id);
	
	AuthDetails update(Long id, AuthDetails details);
}
