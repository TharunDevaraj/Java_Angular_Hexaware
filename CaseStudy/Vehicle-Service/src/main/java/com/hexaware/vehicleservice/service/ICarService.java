package com.hexaware.vehicleservice.service;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.vehicleservice.dto.CarDTO;
import com.hexaware.vehicleservice.entity.Car;
import com.hexaware.vehicleservice.exception.CarNotFoundException;

public interface ICarService {
	
	public Car addCar(CarDTO carDTO);
	
	public CarDTO getCarById(Long carId) throws CarNotFoundException;
	
	public List<Car> getAllCars();
	
	public Car updateCar(Long carId, CarDTO updatedCarDTO) throws CarNotFoundException;
	
	public void deleteCarById(Long carId) throws CarNotFoundException;

	public Car updateVehicleStatus(Long carId, String newStatus) throws CarNotFoundException;
	public Car updateCarPricing(Long carId, double newPricePerDay) throws CarNotFoundException;
	public List<Car> getAvailableCars();
	public List<Car> findAvailableCarsByFilter(String location, int passengerCapacity, LocalDate startDate, LocalDate endDate);
	public Car updateCarAvailability(Long carId,String availability) throws CarNotFoundException;
	public List<Car> searchVehicles(String location, int passengerCapacity);
	boolean isCarAvailable(Long carId, LocalDate startDate, LocalDate endDate);


}
