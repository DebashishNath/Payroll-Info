package com.payroll.payrollWebService.payload.response;

import com.payroll.payrollWebService.models.auth.Role;

import java.util.HashSet;
import java.util.Set;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private Set<Role> roles=new HashSet<>();
	private String returnMessage;

	public JwtResponse(String accessToken, Long id, String username, String email,
					   Set<Role> userRoles, String returnMessage) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = userRoles;
		this.returnMessage = returnMessage;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String errorMessage) {
		this.returnMessage = returnMessage;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> userRoles) {
		this.roles = userRoles;
	}
}
