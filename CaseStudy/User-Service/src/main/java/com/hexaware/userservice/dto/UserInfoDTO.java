package com.hexaware.userservice.dto;

/**
 * DTO for capturing user request details such as userId,
 * email and their roles.
 */


public class UserInfoDTO {
	private Long userId;
    private String userName; 
    private String email;
    private String roles;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
    
    
}
