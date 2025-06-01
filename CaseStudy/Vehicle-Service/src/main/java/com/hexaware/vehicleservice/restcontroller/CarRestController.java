package com.hexaware.vehicleservice.restcontroller;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.vehicleservice.dto.CarDTO;
import com.hexaware.vehicleservice.entity.Car;
import com.hexaware.vehicleservice.exception.CarNotFoundException;
import com.hexaware.vehicleservice.service.ICarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/car")
public class CarRestController {

	@Autowired
    ICarService carService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Car> addCar(@RequestBody @Valid CarDTO carDTO) {
        Car savedCar = carService.addCar(carDTO);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }

    @PutMapping("/update/{carId}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Car> updateCar(@PathVariable Long carId, @RequestBody @Valid CarDTO carDTO) throws CarNotFoundException {
        Car updatedCar = carService.updateCar(carId,carDTO);
        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
    }

    @GetMapping("/get/{carId}")
    @PreAuthorize("hasAnyRole('admin','user')")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long carId) throws CarNotFoundException {
        CarDTO carDTO = carService.getCarById(carId);
        return new ResponseEntity<>(carDTO, HttpStatus.OK);
    }

    @GetMapping("/get")
    @PreAuthorize("hasAnyRole('admin','user')")
    public ResponseEntity<List<Car>> getAllCars() {
        return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
    }

    @GetMapping("/available")
    @PreAuthorize("hasAnyRole('admin','user')")
    public ResponseEntity<List<Car>> getAvailableCars() {
        return new ResponseEntity<>(carService.getAvailableCars(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{carId}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> deleteCar(@PathVariable Long carId) throws CarNotFoundException {
        carService.deleteCarById(carId);
        return new ResponseEntity<>("Car deleted successfully!", HttpStatus.OK);
    }
    
    @PutMapping("/updateprice/{id}/{price}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Car> updateCarPrice(@PathVariable Long id, @PathVariable double price) throws CarNotFoundException
    {
    	Car updatedCar = carService.updateCarPricing(id,price);
        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
    }
    
    @GetMapping("/availablecarsbyfilter")
    @PreAuthorize("hasAnyRole('admin','user','agent')")
    public ResponseEntity<List<Car>> getAvailableCarsByFilter(@RequestParam String location,@RequestParam int passengerCapacity,@RequestParam LocalDate startDate,@RequestParam LocalDate endDate)
    {
    	return new ResponseEntity<List<Car>>(carService.findAvailableCarsByFilter(location, passengerCapacity, startDate, endDate), HttpStatus.OK);
    }
  
    @PutMapping("updateStatus/{carId}/{status}")
    @PreAuthorize("hasAnyRole('admin','agent')")
    public ResponseEntity<Car> updateVehicleStatus(@PathVariable Long carId,@PathVariable String status) throws CarNotFoundException
    {
    	return new ResponseEntity<Car>(carService.updateVehicleStatus(carId, status), HttpStatus.OK);
    }
    
}
