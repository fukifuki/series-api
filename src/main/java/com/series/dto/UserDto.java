package com.series.dto;

import com.series.model.Role;

public class UserDto {
	
	private Long id;
	private String username;
	private String password;
//	TODO Add matchingPassword
	private String email;
	private String role;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

//	maybe I should put this role name adjustment into private method... ?
	public void setRole(Role role) {
		this.role = role.getName().replace("ROLE_", "");
	}
	
}
