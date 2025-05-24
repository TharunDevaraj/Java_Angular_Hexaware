package com.hexaware.vehicleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.vehicleservice.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long>{

}