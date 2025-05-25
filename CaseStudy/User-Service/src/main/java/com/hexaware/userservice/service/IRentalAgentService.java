package com.hexaware.userservice.service;

import java.util.List;

import com.hexaware.userservice.dto.RentalAgentDTO;
import com.hexaware.userservice.entity.RentalAgent;


public interface IRentalAgentService {
	
	public RentalAgent registerRentalAgent(RentalAgentDTO rentalAgentDTO);
	
	public RentalAgentDTO getRentalAgentById(Long rentalAgentId);
	
	public List<RentalAgent> getAllRentalAgents();
	
	public RentalAgent updateRentalAgent(RentalAgentDTO updatedRentalAgentDTO);
	
	public void deleteRentalAgentById(Long rentalAgentId);
	
	public RentalAgentDTO login(String email, String password);

}
