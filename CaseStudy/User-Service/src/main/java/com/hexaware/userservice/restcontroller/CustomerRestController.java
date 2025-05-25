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

import com.hexaware.userservice.dto.CustomerDTO;
import com.hexaware.userservice.entity.Customer;
import com.hexaware.userservice.service.ICustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {
	
	@Autowired
	ICustomerService customerService;
	
	@PostMapping("/register")
	 public Customer registerCustomer(@RequestBody CustomerDTO customerDTO) {
	        return customerService.registerCustomer(customerDTO);
	 }

	 @PostMapping("/login")
	 public CustomerDTO loginCustomer(@RequestParam String email, @RequestParam String password) {
	        return customerService.login(email, password);
	    }

	 @GetMapping("/get/{customerId}")
	 public CustomerDTO getCustomerById(@PathVariable Long customerId) {
	        return customerService.getCustomerById(customerId);
	 }

	 @PutMapping("/update")
	 public Customer updateCustomer(@RequestBody CustomerDTO customerDTO) {
	        return customerService.updateCustomer(customerDTO);
	 }

	 @DeleteMapping("/delete/{customerId}")
	 public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId) {
	        customerService.deleteCustomerById(customerId);
	        return new ResponseEntity<String>("Deleted!!", HttpStatus.OK);
	 }

}
