package com.hexaware.userservice.service;

import java.util.List;

import com.hexaware.userservice.dto.CustomerDTO;
import com.hexaware.userservice.entity.Customer;

public interface ICustomerService {
	
	public Customer registerCustomer(CustomerDTO customerDTO);
	
	public CustomerDTO getCustomerById(Long customerId);
	
	public List<Customer> getAllCustomers();
	
	public Customer updateCustomer(CustomerDTO updatedCustomerDTO);
	
	public void deleteCustomerById(Long customerId);
	
	public CustomerDTO login(String email, String password);

}
