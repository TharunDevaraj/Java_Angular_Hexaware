package com.hexaware.userservice.dto;

public class AdminDTO {
	
	private Long adminId;
	 private String adminName;
	 private String email;
	 private String phone;
	 private String password;
	 private String address;
	 
	 public AdminDTO()
	 {
		 
	 }
	 
	public AdminDTO(Long adminId, String adminName, String email, String phone, String password, String address) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.address = address;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	 
	 

}
