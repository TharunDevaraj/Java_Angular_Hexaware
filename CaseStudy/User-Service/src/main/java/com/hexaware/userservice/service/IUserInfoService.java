package com.hexaware.userservice.service;

import java.util.List;

import com.hexaware.userservice.dto.UserInfoDTO;
import com.hexaware.userservice.entity.UserInfo;
import com.hexaware.userservice.exception.UserNotFoundException;

public interface IUserInfoService {
	
	public String registerUser(UserInfo userInfo);
	
	public List<UserInfoDTO> getAllUsers();
	
	public UserInfoDTO getUserById(Long id) throws UserNotFoundException;
	
	public UserInfoDTO updateUser(Long id, UserInfo updatedUser) throws UserNotFoundException;
	
	public void deactivateUser(Long id) throws UserNotFoundException;
	

}
