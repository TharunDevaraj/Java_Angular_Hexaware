package com.hexaware.userservice.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import jakarta.validation.Valid;

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
	public ResponseEntity<String> addUser(@RequestBody @Valid UserInfo userInfo)
	{
		return new ResponseEntity<String>(userService.registerUser(userInfo), HttpStatus.CREATED);
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
	
	 	@GetMapping("/get")
	    @PreAuthorize("hasRole('admin')")
	    public ResponseEntity<List<UserInfoDTO>> getAllUsers() {
	        return new ResponseEntity<List<UserInfoDTO>>(userService.getAllUsers(), HttpStatus.FOUND);
	    }

	    @GetMapping("get/{id}")
	    @PreAuthorize("hasRole('admin')")
	    public ResponseEntity<UserInfoDTO> getUserById(@PathVariable Long id) throws UserNotFoundException {
	        return  new ResponseEntity<UserInfoDTO>(userService.getUserById(id), HttpStatus.FOUND);
	    }

	    @PutMapping("update/{id}")
	    @PreAuthorize("hasRole('admin')")
	    public ResponseEntity<UserInfoDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UserInfo user) throws UserNotFoundException {
	        return new ResponseEntity<UserInfoDTO>(userService.updateUser(id, user), HttpStatus.OK);
	    }

	    @DeleteMapping("deactivate/{id}")
	    @PreAuthorize("hasRole('admin')")
	    public ResponseEntity<String> deactivateUser(@PathVariable Long id) throws UserNotFoundException {
	        userService.deactivateUser(id);
	        return new ResponseEntity<String>("User deactivated successfully",HttpStatus.OK);
	    }
}
