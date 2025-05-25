package com.hexaware.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hexaware.userservice.dto.CustomerDTO;
import com.hexaware.userservice.entity.Customer;
import com.hexaware.userservice.repository.CustomerRepository;

public class CustomerServiceImp implements ICustomerService{

	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Customer registerCustomer(CustomerDTO customerDTO) {
		
		 Customer customer = new Customer();
	     customer.setCustomerName(customerDTO.getCustomerName());
	     customer.setEmail(customerDTO.getEmail());
	     customer.setPassword(customerDTO.getPassword());
	     customer.setPhone(customerDTO.getPhone());
	     customer.setAddress(customerDTO.getAddress());

	     return customerRepository.save(customer);
	}

	@Override
	public CustomerDTO getCustomerById(Long customerId) {
		
		Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            return null;
        }
        return mapToDTO(customer);
	}

	@Override
	public List<Customer> getAllCustomers() {
		
		return customerRepository.findAll();
	}

	@Override
	public Customer updateCustomer(CustomerDTO updatedCustomerDTO) {
		
		Customer existingCustomer = customerRepository.findById(updatedCustomerDTO.getCustomerId()).orElse(null);
        if (existingCustomer == null) {
            return null;
        }

        existingCustomer.setCustomerName(updatedCustomerDTO.getCustomerName());
        existingCustomer.setEmail(updatedCustomerDTO.getEmail());
        existingCustomer.setPassword(updatedCustomerDTO.getPassword());
        existingCustomer.setPhone(updatedCustomerDTO.getPhone());
        existingCustomer.setAddress(updatedCustomerDTO.getAddress());

        return customerRepository.save(existingCustomer);
	}

	@Override
	public void deleteCustomerById(Long customerId) {
		
		customerRepository.deleteById(customerId);
	}

	@Override
	public CustomerDTO login(String email, String password) {
		
		Customer customer = customerRepository.findByEmailAndPassword(email, password).orElse(null);
        if (customer == null) {
            return null;
        }
        return mapToDTO(customer);
	}
	
	private CustomerDTO mapToDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerId(customer.getCustomerId());
        dto.setCustomerName(customer.getCustomerName());
        dto.setEmail(customer.getEmail());
        dto.setPassword(customer.getPassword());
        dto.setPhone(customer.getPhone());
        dto.setAddress(customer.getAddress());
        return dto;
    }

}
