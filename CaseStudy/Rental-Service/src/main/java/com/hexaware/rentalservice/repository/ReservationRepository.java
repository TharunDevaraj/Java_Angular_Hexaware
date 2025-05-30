package com.hexaware.rentalservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.rentalservice.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	List<Reservation> findByCustomerId(Long customerId);
    List<Reservation> findByCarId(Long carId);
}
