package com.hexaware.userservice.service;

import java.util.List;

import com.hexaware.userservice.entity.RentalAgent;


public interface IRentalAgentService {
	
	public RentalAgent registerRentalAgent(RentalAgent rentalAgent);
	
	public RentalAgent getRentalAgentById(Long rentalAgentId);
	
	public List<RentalAgent> getAllRentalAgents();
	
	public RentalAgent updateRentalAgent(RentalAgent updatedRentalAgent);
	
	public void deleteRentalAgentById(Long rentalAgentId);
	
	public RentalAgent login(String email, String password);

}
