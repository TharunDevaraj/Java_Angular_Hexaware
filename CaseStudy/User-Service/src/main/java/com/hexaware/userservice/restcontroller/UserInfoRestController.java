package com.hexaware.userservice.restcontroller;

import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hexaware.userservice.dto.AuthRequest;
import com.hexaware.userservice.dto.CarDTO;
import com.hexaware.userservice.dto.PaymentDTO;
import com.hexaware.userservice.dto.ReservationDTO;
import com.hexaware.userservice.dto.UserInfoDTO;
import com.hexaware.userservice.entity.UserInfo;
import com.hexaware.userservice.exception.UserNotFoundException;
import com.hexaware.userservice.service.CarServiceClient;
import com.hexaware.userservice.service.JwtService;
import com.hexaware.userservice.service.UserInfoServiceImp;

import jakarta.validation.Valid;

/**
 * Date: 02-06-2025
 * Author: Tharun D
 * REST controller for managing User operations such as USER registration,
 * login, and user deactivation.
 */

@RestController
@RequestMapping("/api/users")
public class UserInfoRestController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	UserInfoServiceImp userService;
	
	@Autowired
	CarServiceClient carServiceClient;
	
	@Autowired
    private RestTemplate restTemplate;
	
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
	   
        // Public level operations
	    
	    private final String CAR_SERVICE_BASE_URL = "http://localhost:8181/api/cars";

	    @GetMapping("/getcarbyid/{carId}")
	    public CarDTO getCarById(@PathVariable Long carId) {
	        return restTemplate.getForObject(CAR_SERVICE_BASE_URL + "/get/" + carId, CarDTO.class);
	    }

	    @GetMapping("/getallcars")
	    public List<CarDTO> getAllCars() {
	        CarDTO dtos[]= restTemplate.getForObject(CAR_SERVICE_BASE_URL + "/get", CarDTO[] .class);
	        List<CarDTO> cars = Arrays.asList(dtos);
	        return cars;
	        
	    }

	    @GetMapping("/getavailable")
	    public List<CarDTO> getAvailableCars() {
	    	CarDTO dtos[]=restTemplate.getForObject(CAR_SERVICE_BASE_URL + "/available", CarDTO[].class);
	        List<CarDTO> cars = Arrays.asList(dtos);
	        return cars;
	    }
	    
	    //CUSTOMER level operations
	    
	    private final String RESERVATION_SERVICE_BASE_URL = "http://localhost:8282/api/reservation";
	    
	    @PostMapping("/makereservation")
	    public ResponseEntity<ReservationDTO> makeReservation(@RequestBody ReservationDTO reservationDTO)
	    {
	    	ReservationDTO reservation = restTemplate.postForObject(RESERVATION_SERVICE_BASE_URL+"/create ", reservationDTO,ReservationDTO.class);
	    	return new ResponseEntity<ReservationDTO>(reservation, HttpStatus.CREATED);
	    }
	    
	    private final String PAYMENT_SERVICE_BASE_URL = "http://localhost:8282/api/payment";
	    
	    @PostMapping("/makepayment")
	    public ResponseEntity<PaymentDTO> makePayment(@RequestBody PaymentDTO paymentDTO)
	    {
	    	PaymentDTO payment = restTemplate.postForObject(PAYMENT_SERVICE_BASE_URL+"/make ", paymentDTO,PaymentDTO.class);
	    	return new ResponseEntity<PaymentDTO>(payment, HttpStatus.CREATED);
	    }
	    
	    
	    //ADMIN level operations
	    @PostMapping("/admin/add-car")
	    public ResponseEntity<CarDTO> addCar(@RequestBody CarDTO carDTO, @RequestHeader("Authorization") String authHeader) {
	        String jwtToken = authHeader.replace("Bearer ", "");
	        return carServiceClient.addCar(carDTO, jwtToken);
	    }
	    
	    @PutMapping("/admin/update-car/{carId}")
	    public ResponseEntity<CarDTO> updateCar(
	            @PathVariable Long carId,
	            @RequestBody @Valid CarDTO carDTO,
	            @RequestHeader("Authorization") String authHeader) {

	        String jwtToken = authHeader.replace("Bearer ", "");
	        return carServiceClient.updateCar(carId, carDTO, jwtToken);
	    }
	    
	    
}
