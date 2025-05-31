package com.hexaware.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.userservice.dto.AdminDTO;
import com.hexaware.userservice.entity.Admin;
import com.hexaware.userservice.repository.AdminRepository;

@Service
public class AdminServiceImp implements IAdminService{

	
	@Autowired
	AdminRepository adminRepository;

	@Override
	public Admin registerAdmin(AdminDTO adminDTO) {
		
		Admin admin = new Admin();
		
		admin.setAdminName(adminDTO.getAdminName());
		admin.setEmail(adminDTO.getEmail());
		admin.setPassword(adminDTO.getPassword());
		admin.setPhone(adminDTO.getPhone());
		admin.setAddress(adminDTO.getAddress());
		
		return adminRepository.save(admin);
	}

	@Override
	public AdminDTO getAdminById(Long adminId) {
		
		Admin admin = adminRepository.findById(adminId).orElse(null);
		if(admin==null)
		{
			return null;
		}
		
		return mapToDTO(admin);
	}

	@Override
	public List<Admin> getAllAdmins() {
		
		return adminRepository.findAll();
	}

	@Override
	public Admin updateAdmin(AdminDTO updatedAdminDTO) {

		Admin existingAdmin = adminRepository.findById(updatedAdminDTO.getAdminId()).orElse(null);
	    if (existingAdmin == null) {
	        return null; 
	    }
		
		existingAdmin.setAdminName(updatedAdminDTO.getAdminName());
		existingAdmin.setEmail(updatedAdminDTO.getEmail());
		existingAdmin.setPassword(updatedAdminDTO.getPassword());
		existingAdmin.setPhone(updatedAdminDTO.getPhone());
		existingAdmin.setAddress(updatedAdminDTO.getAddress());
		
		return adminRepository.save(existingAdmin);
	}

	@Override
	public void deleteAdminById(Long adminId) {
		
		Admin existingAdmin = adminRepository.findById(adminId).orElse(null);
	    if (existingAdmin == null) {
	        //throw
	    }
		adminRepository.deleteById(adminId);
		
	}

	@Override
	public AdminDTO login(String email, String password) {
				
		Admin admin=adminRepository.findByEmailAndPassword(email, password).orElse(null);
		if(admin==null)
			return null;
		
		return mapToDTO(admin);
	}
	
	private AdminDTO mapToDTO(Admin admin) {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setAdminId(admin.getAdminId());
        adminDTO.setAdminName(admin.getAdminName());
        adminDTO.setEmail(admin.getEmail());
        adminDTO.setPassword(admin.getPassword());
        adminDTO.setPhone(admin.getPhone());
        adminDTO.setAddress(admin.getAddress());
        return adminDTO;
    }
	
	

}
