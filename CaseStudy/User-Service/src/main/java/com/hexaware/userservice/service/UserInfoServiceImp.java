package com.hexaware.userservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.userservice.dto.UserInfoDTO;
import com.hexaware.userservice.entity.UserInfo;
import com.hexaware.userservice.exception.UserNotFoundException;
import com.hexaware.userservice.repository.UserInfoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserInfoServiceImp implements IUserInfoService{

	@Autowired
	UserInfoRepository userInfoRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public String registerUser(UserInfo userInfo) {
		
		if (userInfoRepository.findByUserName(userInfo.getUserName()) != null) {
			log.warn("Username already exists");
		    return "Username already exists!";
		}
		log.info("Registering new user with username={}, email={} and role={}",userInfo.getUserName(), userInfo.getEmail(), userInfo.getRoles());
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		
		 UserInfo savedUser = userInfoRepository.save(userInfo);
	     log.debug("User registered successfully: {}", savedUser);
	     return "User Registered!";
	}

	@Override
	public List<UserInfoDTO> getAllUsers() {
		log.info("Fetching all users");
		List<UserInfoDTO> usersDetails=new ArrayList<>();
		
		List<UserInfo> users = userInfoRepository.findAll();
		
		for(UserInfo userInfo : users)
		{
			usersDetails.add(EntityToDTO(userInfo));
		}
		log.debug("Total users fetched: {}", users.size());
		return usersDetails;
	}

	@Override
	public UserInfoDTO getUserById(Long id) throws UserNotFoundException {
		log.info("Fetching user with id={} ", id);
		UserInfo user = userInfoRepository.findById(id).orElse(null);
		if(user==null)
		{
			log.warn("User with id={} not found", id);
			throw new UserNotFoundException();
		}
		log.debug("User found: {}", user);
		return EntityToDTO(user);
	}

	@Override
	public UserInfoDTO updateUser(Long id, UserInfo updatedUser) throws UserNotFoundException {
		log.info("Updating user with id={}", id);
		UserInfo existingUserInfo=userInfoRepository.findById(id).orElse(null);
		
		if(existingUserInfo==null)
		{
			log.warn("User with id={} not found for update", id);
			throw new UserNotFoundException();
		}
		existingUserInfo.setUserName(updatedUser.getUserName());
        existingUserInfo.setEmail(updatedUser.getEmail());
        existingUserInfo.setRoles(updatedUser.getRoles());

        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            existingUserInfo.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        log.debug("User with id={} updated successfully: {}", id, existingUserInfo);
        return EntityToDTO(existingUserInfo);
		
		
	}

	 @Override
	    public void deactivateUser(Long id) throws UserNotFoundException {
		 log.info("Deactivating user with id={}", id);
	        UserInfo user = userInfoRepository.findById(id).orElse(null);
	        if(user==null)
	        {
	        	log.warn("Attempted to deactivate non-existing user with id={}", id);
	        	throw new UserNotFoundException();
	        }
	        user.setRoles("INACTIVE"); 
	        log.debug("User with id={} deactivated successfully", id);
	        userInfoRepository.save(user);
	    }
	 
	 private UserInfoDTO EntityToDTO(UserInfo userInfo)
	 {
		 UserInfoDTO userInfoDTO=new UserInfoDTO();
		 userInfoDTO.setUserId(userInfo.getUserId());
		 userInfoDTO.setUserName(userInfo.getUserName());
		 userInfoDTO.setEmail(userInfo.getEmail());
		 userInfoDTO.setRoles(userInfo.getRoles());
		 return userInfoDTO;
	 }

}
