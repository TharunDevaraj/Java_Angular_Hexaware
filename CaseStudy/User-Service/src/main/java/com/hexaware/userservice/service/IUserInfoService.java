package com.hexaware.userservice.service;

import java.util.List;

import com.hexaware.userservice.dto.UserInfoDTO;
import com.hexaware.userservice.entity.UserInfo;
import com.hexaware.userservice.exception.UserNotFoundException;

public interface IUserInfoService {
	
	public String registerUser(UserInfoDTO userInfoDTO);
	
	public List<UserInfo> getAllUsers();
	
	public UserInfo getUserById(Long id);
	
	public UserInfo updateUser(Long id, UserInfo updatedUser) throws UserNotFoundException;
	
	public void deactivateUser(Long id) throws UserNotFoundException;
	

}
