package com.hexaware.userservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.hexaware.userservice.dto.UserInfoDTO;
import com.hexaware.userservice.entity.UserInfo;
import com.hexaware.userservice.exception.UserNameAlreadyExistsException;
import com.hexaware.userservice.exception.UserNotFoundException;

public interface IUserInfoService {
	
	public UserInfoDTO registerUser(UserInfo userInfo) throws UserNameAlreadyExistsException;
	
	public List<UserInfoDTO> getAllUsers();
	
	public UserInfoDTO getUserById(Long id) throws UserNotFoundException;
	
	public UserInfoDTO updateUser(Long id, UserInfo updatedUser) throws UserNotFoundException;
	
	public String deactivateUser(Long id) throws UserNotFoundException;
	

}
