package com.hexaware.userservice.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.userservice.dto.AdminDTO;
import com.hexaware.userservice.entity.Admin;

@SpringBootTest
class AdminServiceImpTest {

	@Autowired
	IAdminService adminService;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	//@Test
	void testRegisterAdmin() {
		
		AdminDTO admin = new AdminDTO(null, "Tagore", "tagore@gmail.com", "4455667788", "12345678", "12, Cross Street, Chennai");
		
		Admin testAdmin = adminService.registerAdmin(admin);
		
		assertNotNull(testAdmin);
		
		assertEquals("Tagore", testAdmin.getAdminName());
	}

	//@Test
	void testGetAdminById() {
		
		Long adminId=(long) 1;
		AdminDTO adminDTO = adminService.getAdminById(adminId);
		
		assertEquals("tagore@gmail.com", adminDTO.getEmail());
		
	}

	@Test
	void testGetAllAdmins() {
		
		List<Admin> allAdmins = adminService.getAllAdmins();
		
	}

	@Test
	void testUpdateAdmin() {
		
	}

	@Test
	void testDeleteAdminById() {
		
	}

	@Test
	void testLogin() {
		
	}

}
