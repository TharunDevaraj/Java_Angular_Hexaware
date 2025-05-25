package com.hexaware.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.userservice.entity.RentalAgent;

@Repository
public interface RentalAgentRepository extends JpaRepository<RentalAgent, Long>{

	Optional<RentalAgent> findByEmailAndPassword(String email, String password);
}
