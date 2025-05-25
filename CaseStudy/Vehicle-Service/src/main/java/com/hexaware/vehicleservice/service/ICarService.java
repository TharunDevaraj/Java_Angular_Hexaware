package com.hexaware.vehicleservice.service;

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

}
