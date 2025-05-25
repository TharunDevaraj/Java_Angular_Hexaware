package com.hexaware.vehicleservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.vehicleservice.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long>{

	 List<Car> findByCarStatus(String status);
	 List<Car> findByLocationAndPassengerCapacityGreaterThanEqualAndCarStatus( String location, int passengerCapacity, String status);
}