package com.hexaware.userservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class RentalAgent {
	
	 @Id 
	 @GeneratedValue
	 private Long rentalAgentId;
	 private String rentalAgentName;
	 private String email;
	 private String phone;
	 private String password;
	 private String address;
	 
	 public RentalAgent()
	 {
		 
	 }
	 
	public RentalAgent(Long rentalAgentId, String rentalAgentName, String email, String phone, String password,
			String address) {
		super();
		this.rentalAgentId = rentalAgentId;
		this.rentalAgentName = rentalAgentName;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.address = address;
	}

	public Long getRentalAgentId() {
		return rentalAgentId;
	}

	public void setRentalAgentId(Long rentalAgentId) {
		this.rentalAgentId = rentalAgentId;
	}

	public String getRentalAgentName() {
		return rentalAgentName;
	}

	public void setRentalAgentName(String rentalAgentName) {
		this.rentalAgentName = rentalAgentName;
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

	@Override
	public String toString() {
		return "RentalAgent [rentalAgentId=" + rentalAgentId + ", rentalAgentName=" + rentalAgentName + ", email="
				+ email + ", phone=" + phone + ", password=" + password + ", address=" + address + "]";
	}
	
}
