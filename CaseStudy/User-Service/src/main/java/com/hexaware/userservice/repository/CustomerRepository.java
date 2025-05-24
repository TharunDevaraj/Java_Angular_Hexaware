package com.hexaware.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.userservice.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
