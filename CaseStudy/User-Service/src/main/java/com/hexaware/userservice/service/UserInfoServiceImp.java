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

@Service
public class UserInfoServiceImp implements IUserInfoService{

	@Autowired
	UserInfoRepository userInfoRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public String registerUser(UserInfo userInfo) {
		
		if (userInfoRepository.findByUserName(userInfo.getUserName()) != null) {
		    return "Username already exists!";
		}
		
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userInfoRepository.save(userInfo);
		return "User added!";
	}

	@Override
	public List<UserInfoDTO> getAllUsers() {
		
		List<UserInfoDTO> usersDetails=new ArrayList<>();
		
		List<UserInfo> users = userInfoRepository.findAll();
		
		for(UserInfo userInfo : users)
		{
			usersDetails.add(EntityToDTO(userInfo));
		}
		return usersDetails;
	}

	@Override
	public UserInfoDTO getUserById(Long id) throws UserNotFoundException {
		
		UserInfo user = userInfoRepository.findById(id).orElse(null);
		if(user==null)
		{
			throw new UserNotFoundException();
		}
		return EntityToDTO(user);
	}

	@Override
	public UserInfoDTO updateUser(Long id, UserInfo updatedUser) throws UserNotFoundException {
		
		UserInfo existingUserInfo=userInfoRepository.findById(id).orElse(null);
		
		if(existingUserInfo==null)
		{
			throw new UserNotFoundException();
		}
		existingUserInfo.setUserName(updatedUser.getUserName());
        existingUserInfo.setEmail(updatedUser.getEmail());
        existingUserInfo.setRoles(updatedUser.getRoles());

        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            existingUserInfo.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        return EntityToDTO(existingUserInfo);
		
		
	}

	 @Override
	    public void deactivateUser(Long id) throws UserNotFoundException {
	        UserInfo user = userInfoRepository.findById(id).orElse(null);
	        if(user==null)
	        {
	        	throw new UserNotFoundException();
	        }
	        user.setRoles("INACTIVE"); 
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
