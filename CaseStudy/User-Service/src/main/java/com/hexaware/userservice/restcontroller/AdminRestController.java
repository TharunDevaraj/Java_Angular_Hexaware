package com.hexaware.userservice.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.userservice.dto.AdminDTO;
import com.hexaware.userservice.entity.Admin;
import com.hexaware.userservice.service.IAdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {
	
	@Autowired
	IAdminService adminService;
	
	 @PostMapping("/register")
	 public Admin registerAdmin(@RequestBody AdminDTO adminDTO) {
	        return adminService.registerAdmin(adminDTO);
	 }

	 @PostMapping("/login")
	 public AdminDTO loginAdmin(@RequestParam String email, @RequestParam String password) {
	        return adminService.login(email, password);
	    }

	 @GetMapping("/get/{adminId}")
	 public AdminDTO getAdminById(@PathVariable Long adminId) {
	        return adminService.getAdminById(adminId);
	 }

	 @PutMapping("/update")
	 public Admin updateAdmin(@RequestBody AdminDTO adminDTO) {
	        return adminService.updateAdmin(adminDTO);
	 }

	 @DeleteMapping("/delete/{adminId}")
	 public ResponseEntity<String> deleteAdmin(@PathVariable Long adminId) {
	        adminService.deleteAdminById(adminId);
	        return new ResponseEntity<String>("Deleted!!", HttpStatus.OK);
	 }

}
