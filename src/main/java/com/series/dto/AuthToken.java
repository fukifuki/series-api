package com.series.dto;

public class AuthToken {
	
	private String jwtString;
	
	public AuthToken(String jwtString) {
		this.jwtString = jwtString;
	}

	public String getJwtString() {
		return jwtString;
	}

	public void setJwtString(String jwtString) {
		this.jwtString = jwtString;
	}
	
}
