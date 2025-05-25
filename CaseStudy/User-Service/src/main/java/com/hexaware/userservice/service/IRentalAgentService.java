package com.hexaware.userservice.service;

import java.util.List;

import org.aspectj.weaver.loadtime.Agent;

public interface IRentalAgentService {
	
	public Agent registerAgent(Agent agent);
	
	public Agent getAgentById(Long agentId);
	
	public List<Agent> getAllAgents();
	
	public Agent updateAgent(Agent updatedAgent);
	
	public void deleteAgent(Long agentId);
	
	public Agent login(String email, String password);

}
