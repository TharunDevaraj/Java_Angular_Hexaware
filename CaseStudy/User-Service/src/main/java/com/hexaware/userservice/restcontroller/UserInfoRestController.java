package com.hexaware.userservice.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.userservice.dto.AuthRequest;
import com.hexaware.userservice.entity.UserInfo;
import com.hexaware.userservice.service.JwtService;
import com.hexaware.userservice.service.UserInfoServiceImp;

@RestController
@RequestMapping("/users")
public class UserInfoRestController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	UserInfoServiceImp userService;
	
	@PostMapping("/register")
	public String addUser(@RequestBody UserInfo userInfo)
	{
		return userService.registerUser(userInfo);
	}
	
	@GetMapping("/")
	public String hello()
	{
		return "Hello";
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
