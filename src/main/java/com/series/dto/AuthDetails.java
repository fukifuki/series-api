package com.series.dto;

import com.series.model.Role;

public class AuthDetails {
	
	private String username;
	private String role;
	private boolean enabled;
	
	
	public AuthDetails() {}	
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role.getName().replace("ROLE_", "");
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
