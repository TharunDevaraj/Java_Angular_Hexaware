package com.hexaware.vehicleservice.service;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.vehicleservice.dto.CarDTO;
import com.hexaware.vehicleservice.entity.Car;

public interface ICarService {
	
	public Car addCar(CarDTO carDTO);
	
	public CarDTO getCarById(Long carId);
	
	public List<Car> getAllCars();
	
	public Car updateCar(CarDTO updatedCarDTO);
	
	public void deleteCarById(Long carId);

	public List<Car> getAvailableCars();
	public void markCarAsUnavailable(Long carId);
	public void markCarAsAvailable(Long carId);
	public List<Car> searchVehicles(String location, int passengerCapacity);
	boolean isCarAvailable(Long carId, LocalDate startDate, LocalDate endDate);


}
