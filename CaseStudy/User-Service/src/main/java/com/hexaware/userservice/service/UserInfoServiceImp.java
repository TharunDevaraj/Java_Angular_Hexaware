package com.hexaware.userservice.service;

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
	public String registerUser(UserInfoDTO userInfoDTO) {
		
		UserInfo userInfo=new UserInfo();
		
		userInfo.setUserId(userInfoDTO.getUserId());
		userInfo.setUserName(userInfoDTO.getUserName());
		userInfo.setEmail(userInfoDTO.getEmail());
		userInfo.setPassword(userInfoDTO.getPassword());
		userInfo.setRoles(userInfoDTO.getRoles());
		
		if (userInfoRepository.findByUserName(userInfo.getUserName()) != null) {
		    return "Username already exists!";
		}
		
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userInfoRepository.save(userInfo);
		return "User added !";
	}

	@Override
	public List<UserInfo> getAllUsers() {
		
		return userInfoRepository.findAll();
	}

	@Override
	public UserInfo getUserById(Long id) {
		
		return userInfoRepository.findById(id).orElse(null);
	}

	@Override
	public UserInfo updateUser(Long id, UserInfo updatedUser) throws UserNotFoundException {
		
		UserInfo existingUserInfo=getUserById(id);
		
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

        return userInfoRepository.save(existingUserInfo);
		
		
	}

	 @Override
	    public void deactivateUser(Long id) throws UserNotFoundException {
	        UserInfo user = getUserById(id);
	        if(user==null)
	        {
	        	throw new UserNotFoundException();
	        }
	        user.setRoles("INACTIVE"); 
	        userInfoRepository.save(user);
	    }

}
