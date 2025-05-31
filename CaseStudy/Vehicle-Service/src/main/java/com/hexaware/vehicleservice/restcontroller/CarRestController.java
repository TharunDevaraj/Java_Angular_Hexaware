package com.hexaware.vehicleservice.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.vehicleservice.dto.CarDTO;
import com.hexaware.vehicleservice.entity.Car;
import com.hexaware.vehicleservice.service.ICarService;

@RestController
@RequestMapping("api/car")
public class CarRestController {

	@Autowired
    ICarService carService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Car> addCar(@RequestBody CarDTO carDTO) {
        Car savedCar = carService.addCar(carDTO);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Car> updateCar(@RequestBody CarDTO carDTO) {
        Car updatedCar = carService.updateCar(carDTO);
        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
    }

    @GetMapping("/get/{carId}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long carId) {
        CarDTO carDTO = carService.getCarById(carId);
        return new ResponseEntity<>(carDTO, HttpStatus.OK);
    }

    @GetMapping("/get")
    @PreAuthorize("hasRole('admin','user')")
    public ResponseEntity<List<Car>> getAllCars() {
        return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Car>> getAvailableCars() {
        return new ResponseEntity<>(carService.getAvailableCars(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{carId}")
    public ResponseEntity<String> deleteCar(@PathVariable Long carId) {
        carService.deleteCarById(carId);
        return new ResponseEntity<>("Car deleted successfully!", HttpStatus.OK);
    }
}
