package com.hexaware.userservice.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.userservice.dto.AuthRequest;
import com.hexaware.userservice.dto.UserInfoDTO;
import com.hexaware.userservice.entity.UserInfo;
import com.hexaware.userservice.exception.UserNameAlreadyExistsException;
import com.hexaware.userservice.service.IUserInfoService;
import com.hexaware.userservice.service.JwtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/authenticate")
public class AuthenticationRestController {

	@Autowired
	JwtService jwtService;
	
	@Autowired
	IUserInfoService userService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
	public ResponseEntity<UserInfoDTO> addUser(@RequestBody @Valid UserInfo userInfo) throws UserNameAlreadyExistsException
	{
		return new ResponseEntity<UserInfoDTO>( userService.registerUser(userInfo), HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		
		

		String token = null;

		if (authentication.isAuthenticated()) {

			// call generate token method from jwtService class

			token = jwtService.generateToken(authRequest.getUsername());

			//logger.info("Token : " + token);

		} else {

			//logger.info("invalid");

			throw new UsernameNotFoundException("UserName or Password in Invalid / Invalid Request");

		}
			return token;

	}
}
