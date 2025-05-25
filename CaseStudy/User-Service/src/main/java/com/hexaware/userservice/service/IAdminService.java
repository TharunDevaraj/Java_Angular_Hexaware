package com.hexaware.userservice.service;

import java.util.List;

import com.hexaware.userservice.dto.AdminDTO;
import com.hexaware.userservice.entity.Admin;

public interface IAdminService {

	public Admin registerAdmin(AdminDTO adminDTO);
	
	public AdminDTO getAdminById(Long adminId);
	
	public List<Admin> getAllAdmins();
	
	public Admin updateAdmin(AdminDTO updatedAdmin);
	
	public void deleteAdminById(Long adminId);
	
	public AdminDTO login(String email, String password);
	
}
