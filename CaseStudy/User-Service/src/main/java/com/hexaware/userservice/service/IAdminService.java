package com.hexaware.userservice.service;

import java.util.List;

import com.hexaware.userservice.entity.Admin;

public interface IAdminService {

	public Admin registerAdmin(Admin admin);
	
	public Admin getAdminById(Long adminId);
	
	public List<Admin> getAllAdmins();
	
	public Admin updateAdmin(Admin updatedAdmin);
	
	public void deleteAdminById(Long adminId);
	
	public Admin login(String email, String password);
	
}
