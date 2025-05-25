package com.hexaware.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.userservice.dto.RentalAgentDTO;
import com.hexaware.userservice.entity.RentalAgent;
import com.hexaware.userservice.repository.RentalAgentRepository;

@Service
public class RentalAgentServiceImp implements IRentalAgentService{

	
	@Autowired
	RentalAgentRepository rentalAgentRepository;

	@Override
	public RentalAgent registerRentalAgent(RentalAgentDTO rentalAgentDTO) {
		
		RentalAgent rentalAgent=new RentalAgent();
		rentalAgent.setRentalAgentName(rentalAgentDTO.getRentalAgentName());
		rentalAgent.setEmail(rentalAgentDTO.getEmail());
		rentalAgent.setPassword(rentalAgentDTO.getPassword());
		rentalAgent.setPhone(rentalAgentDTO.getPhone());
		rentalAgent.setAddress(rentalAgentDTO.getAddress());
		
		return rentalAgentRepository.save(rentalAgent);
		
	}

	@Override
	public RentalAgentDTO getRentalAgentById(Long rentalAgentId) {
		 
		RentalAgent agent = rentalAgentRepository.findById(rentalAgentId).orElse(null);
	     if (agent == null) {
	         return null;
	     }
	     return mapToDTO(agent);
	}

	@Override
	public List<RentalAgent> getAllRentalAgents() {
		
		return rentalAgentRepository.findAll();
	}

	@Override
	public RentalAgent updateRentalAgent(RentalAgentDTO updatedRentalAgentDTO) {
		
		RentalAgent existingAgent = rentalAgentRepository.findById(updatedRentalAgentDTO.getRentalAgentId()).orElse(null);
        if (existingAgent == null) {
            return null;
        }

        existingAgent.setRentalAgentName(updatedRentalAgentDTO.getRentalAgentName());
        existingAgent.setEmail(updatedRentalAgentDTO.getEmail());
        existingAgent.setPhone(updatedRentalAgentDTO.getPhone());
        existingAgent.setPassword(updatedRentalAgentDTO.getPassword());
        existingAgent.setAddress(updatedRentalAgentDTO.getAddress());

        return rentalAgentRepository.save(existingAgent);
	}

	@Override
	public void deleteRentalAgentById(Long rentalAgentId) {
		
		rentalAgentRepository.deleteById(rentalAgentId);
	}

	@Override
	public RentalAgentDTO login(String email, String password) {
		
		RentalAgent agent = rentalAgentRepository.findByEmailAndPassword(email, password).orElse(null);
        if (agent == null) {
            return null;
        }
        return mapToDTO(agent);
	}
	
	private RentalAgentDTO mapToDTO(RentalAgent agent) {
        RentalAgentDTO dto = new RentalAgentDTO();
        dto.setRentalAgentId(agent.getRentalAgentId());
        dto.setRentalAgentName(agent.getRentalAgentName());
        dto.setEmail(agent.getEmail());
        dto.setPhone(agent.getPhone());
        dto.setPassword(agent.getPassword());
        dto.setAddress(agent.getAddress());
        return dto;
    }
	
}
