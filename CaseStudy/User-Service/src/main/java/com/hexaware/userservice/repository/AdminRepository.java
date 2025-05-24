package com.hexaware.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.userservice.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{

}
