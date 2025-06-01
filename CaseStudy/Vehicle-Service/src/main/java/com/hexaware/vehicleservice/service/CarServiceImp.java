package com.hexaware.vehicleservice.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.vehicleservice.dto.CarDTO;
import com.hexaware.vehicleservice.entity.Car;
import com.hexaware.vehicleservice.exception.CarNotFoundException;
import com.hexaware.vehicleservice.repository.CarRepository;

@Service
public class CarServiceImp implements ICarService{

	
	@Autowired
	CarRepository carRepository;
	@Override
	public Car addCar(CarDTO carDTO) {
		
		Car car=new Car();
		car.setCarName(carDTO.getCarName());
		car.setMake(carDTO.getMake());
		car.setYear(carDTO.getYear());
		car.setCarStatus(carDTO.getCarStatus());
		car.setLocation(carDTO.getLocation());
		car.setPassengerCapacity(carDTO.getPassengerCapacity());
		car.setPricePerDay(carDTO.getPricePerDay());
		
		return carRepository.save(car);
	}

	@Override
	public CarDTO getCarById(Long carId) throws CarNotFoundException {
		
		Car car=carRepository.findById(carId).orElse(null);
		
		if(car==null)
			throw new CarNotFoundException();
		
		 return mapToDTO(car);
		
	}

	@Override
	public List<Car> getAllCars() {
		
		return carRepository.findAll();
	}

	@Override
	public Car updateCar(CarDTO updatedCarDTO) throws CarNotFoundException {
	
		Car existingCar = carRepository.findById(updatedCarDTO.getCarId()).orElse(null);
        if (existingCar==null){
        	throw new CarNotFoundException();
        }
        
        existingCar.setCarName(updatedCarDTO.getCarName());
        existingCar.setYear(updatedCarDTO.getYear());
        existingCar.setMake(updatedCarDTO.getMake());
        existingCar.setCarStatus(updatedCarDTO.getCarStatus());
        existingCar.setLocation(updatedCarDTO.getLocation());
        existingCar.setPassengerCapacity(updatedCarDTO.getPassengerCapacity());
        existingCar.setPricePerDay(updatedCarDTO.getPricePerDay());
        
        return carRepository.save(existingCar);
  
	}

	@Override
	public void deleteCarById(Long carId) throws CarNotFoundException {
		
		Car car=carRepository.findById(carId).orElse(null);
		
		if(car==null)
			throw new CarNotFoundException();
		
		carRepository.deleteById(carId);
	}

	@Override
	public List<Car> getAvailableCars() {
		
		return carRepository.findByCarStatus("Available");
	}

	@Override
	public void markCarAsUnavailable(Long carId) {
		
		Car car = carRepository.findById(carId).orElse(null);
        if (car != null) {
            car.setCarStatus("Unavailable");
            carRepository.save(car);
        }
		
	}

	@Override
	public void markCarAsAvailable(Long carId) {
		
		Car car = carRepository.findById(carId).orElse(null);
        if (car != null) {
            car.setCarStatus("Available");
            carRepository.save(car);
        }
	}

	@Override
	public List<Car> searchVehicles(String location, int passengerCapacity) {
		
		return carRepository.findByLocationAndPassengerCapacityGreaterThanEqualAndCarStatus(
                location, passengerCapacity, "Available");
	}

	@Override
	public boolean isCarAvailable(Long carId, LocalDate startDate, LocalDate endDate) {
		
		Car car = carRepository.findById(carId).orElse(null);
        if (car != null && "Available".equalsIgnoreCase(car.getCarStatus())) {
            return true;
        }
        return false;
	}
	
	@Override
	public Car updateCarPricing(Long carId, double newPricePerDay) throws CarNotFoundException {
	    Car car = carRepository.findById(carId).orElse(null);
	    
	    if(car==null)
			throw new CarNotFoundException();
	              
	    car.setPricePerDay(newPricePerDay);
	    return carRepository.save(car);
	}
	
	private CarDTO mapToDTO(Car car) {
        CarDTO dto = new CarDTO();
        dto.setCarId(car.getCarId());
        dto.setCarName(car.getCarName());
        dto.setYear(car.getYear());
        dto.setMake(car.getMake());
        dto.setCarStatus(car.getCarStatus());
        dto.setLocation(car.getLocation());
        dto.setPassengerCapacity(car.getPassengerCapacity());
        dto.setPricePerDay(car.getPricePerDay());
        return dto;
    }

}
