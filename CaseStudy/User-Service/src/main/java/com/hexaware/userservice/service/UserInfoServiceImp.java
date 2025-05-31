package com.hexaware.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.userservice.entity.UserInfo;
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
		return "User added !";
	}

}
