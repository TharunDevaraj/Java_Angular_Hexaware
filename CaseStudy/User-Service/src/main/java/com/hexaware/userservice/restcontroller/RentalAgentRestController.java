package com.hexaware.userservice.restcontroller;

import java.util.List;

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

import com.hexaware.userservice.dto.RentalAgentDTO;
import com.hexaware.userservice.entity.Admin;
import com.hexaware.userservice.entity.RentalAgent;
import com.hexaware.userservice.service.IRentalAgentService;

@RestController
@RequestMapping("/api/rentalagent")
public class RentalAgentRestController {

	@Autowired
	IRentalAgentService rentalAgentService;
	
	@PostMapping("/register")
	 public RentalAgent registerRentalAgent(@RequestBody RentalAgentDTO rentalAgentDTO) {
	        return rentalAgentService.registerRentalAgent(rentalAgentDTO);
	 }

	 @PostMapping("/login")
	 public RentalAgentDTO loginRentalAgent(@RequestParam String email, @RequestParam String password) {
	        return rentalAgentService.login(email, password);
	    }

	 @GetMapping("/get/{rentalAgentId}")
	 public RentalAgentDTO getRentalAgentById(@PathVariable Long rentalAgentId) {
	        return rentalAgentService.getRentalAgentById(rentalAgentId);
	 }
	 
	 @GetMapping("/get")
	 public List<RentalAgent> getAllRentalAgents()
	 {
		 return rentalAgentService.getAllRentalAgents();
	 }

	 @PutMapping("/update")
	 public RentalAgent updateRentalAgent(@RequestBody RentalAgentDTO rentalAgentDTO) {
	        return rentalAgentService.updateRentalAgent(rentalAgentDTO);
	 }

	 @DeleteMapping("/delete/{rentalAgentId}")
	 public ResponseEntity<String> deleteCustomer(@PathVariable Long rentalAgentId) {
	        rentalAgentService.deleteRentalAgentById(rentalAgentId);
	        return new ResponseEntity<String>("Deleted!!", HttpStatus.OK);
	 }
}
