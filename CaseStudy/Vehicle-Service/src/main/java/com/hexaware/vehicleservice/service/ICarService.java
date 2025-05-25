package com.hexaware.vehicleservice.service;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.vehicleservice.entity.Car;

public interface ICarService {
	
	public Car addCar(Car car);
	
	public Car getCarById(Long carId);
	
	public List<Car> getAllCars();
	
	public Car updateCar(Car updatedCar);
	
	public void deleteCarById(Long carId);

	public List<Car> getAvailableCars();
	public void markCarAsUnavailable(Long carId);
	public void markCarAsAvailable(Long carId);
	public List<Car> searchVehicles(String location, int passengerCapacity, LocalDate startDate, LocalDate endDate);
	boolean isCarAvailable(Long carId, LocalDate startDate, LocalDate endDate);


}
