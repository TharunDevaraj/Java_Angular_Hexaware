package com.hexaware.userservice.service;

import java.util.List;

import com.hexaware.userservice.entity.Customer;

public interface ICustomerService {
	
	public Customer registerCustomer(Customer customer);
	
	public Customer getCustomerById(Long customerId);
	
	public List<Customer> getAllCustomers();
	
	public Customer updateCustomer(Customer updatedCustomer);
	
	public void deleteCustomerById(Long customerId);
	
	public Customer login(String email, String password);

}
