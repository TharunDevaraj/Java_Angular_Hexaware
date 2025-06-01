package com.hexaware.vehicleservice.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hexaware.vehicleservice.dto.CarDTO;
import com.hexaware.vehicleservice.entity.Car;
import com.hexaware.vehicleservice.exception.CarNotFoundException;
import com.hexaware.vehicleservice.repository.CarRepository;

@Service
public class CarServiceImp implements ICarService{

	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
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
	public Car updateCar(Long carId,CarDTO updatedCarDTO) throws CarNotFoundException {
	
		Car existingCar = carRepository.findById(carId).orElse(null);
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
	public List<Car> findAvailableCarsByFilter(String location, int passengerCapacity, LocalDate startDate, LocalDate endDate) {
	    List<Long> bookedCarIds = restTemplate.getForObject("http://localhost:8282/api/reservation/getbookedcars?startDate="+startDate+"&endDate="+endDate, List.class);
	    return carRepository.findAvailableCars(location, passengerCapacity, bookedCarIds);
	}

	@Override
	public List<Car> searchVehicles(String location, int passengerCapacity) {
		
		return carRepository.findByLocationAndPassengerCapacityGreaterThanEqualAndCarStatus(location, passengerCapacity, "available");
	}

	@Override
	public boolean isCarAvailable(Long carId) {
		
		Car car = carRepository.findById(carId).orElse(null);
        if (car != null && "available".equalsIgnoreCase(car.getCarStatus())) {
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

	@Override
	public Car updateVehicleStatus(Long carId, String newStatus) throws CarNotFoundException {
		
		Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException());

        car.setCarStatus(newStatus);
        return carRepository.save(car);
	}

}
