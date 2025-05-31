package com.hexaware.userservice.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.userservice.dto.AuthRequest;
import com.hexaware.userservice.dto.UserInfoDTO;
import com.hexaware.userservice.entity.UserInfo;
import com.hexaware.userservice.exception.UserNotFoundException;
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
	public String addUser(@RequestBody UserInfoDTO userInfoDTO)
	{
		return userService.registerUser(userInfoDTO);
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
	
	 	@GetMapping("/")
	    @PreAuthorize("hasRole('admin')")
	    public List<UserInfo> getAllUsers() {
	        return userService.getAllUsers();
	    }

	    @GetMapping("get/{id}")
	    @PreAuthorize("hasRole('admin')")
	    public UserInfo getUserById(@PathVariable Long id) {
	        return userService.getUserById(id);
	    }

	    @PutMapping("update/{id}")
	    @PreAuthorize("hasRole('admin')")
	    public UserInfo updateUser(@PathVariable Long id, @RequestBody UserInfo user) throws UserNotFoundException {
	        return userService.updateUser(id, user);
	    }

	    @DeleteMapping("deactivate/{id}")
	    @PreAuthorize("hasRole('admin')")
	    public ResponseEntity<String> deactivateUser(@PathVariable Long id) throws UserNotFoundException {
	        userService.deactivateUser(id);
	        return ResponseEntity.ok("User deactivated successfully");
	    }
}
