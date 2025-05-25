package com.hexaware.vehicleservice.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hexaware.vehicleservice.dto.CarDTO;
import com.hexaware.vehicleservice.entity.Car;
import com.hexaware.vehicleservice.repository.CarRepository;

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
	public CarDTO getCarById(Long carId) {
		
		Car car=carRepository.findById(carId).orElse(null);
		
		if(car==null)
			return null;
		
		 return mapToDTO(car);
		
	}

	@Override
	public List<Car> getAllCars() {
		
		return carRepository.findAll();
	}

	@Override
	public Car updateCar(CarDTO updatedCarDTO) {
	
		Car existingCar = carRepository.findById(updatedCarDTO.getCarId()).orElse(null);
        if (existingCar==null){
        	return null;
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
	public void deleteCarById(Long carId) {
		
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
