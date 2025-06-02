package com.hexaware.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.hexaware.userservice.dto.CarDTO;

@Component
public class CarServiceClient {

	@Autowired
    RestTemplate restTemplate;


    public ResponseEntity<CarDTO> addCar(CarDTO carDTO, String jwtToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwtToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CarDTO> request = new HttpEntity<>(carDTO, headers);
        return restTemplate.postForEntity("http://localhost:8181/api/cars/add", request, CarDTO.class);
    }
    

    public ResponseEntity<CarDTO> updateCar(Long carId, CarDTO carDTO, String jwtToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwtToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CarDTO> request = new HttpEntity<>(carDTO, headers);

        String updateUrl = "http://localhost:8181/api/cars/update/" + carId;
        return restTemplate.exchange(updateUrl, HttpMethod.PUT, request, CarDTO.class);
    }

}
