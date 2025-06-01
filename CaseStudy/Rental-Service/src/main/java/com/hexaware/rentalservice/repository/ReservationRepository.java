package com.hexaware.rentalservice.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.rentalservice.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	public List<Reservation> findByCustomerId(Long customerId);
    public List<Reservation> findByCarId(Long carId);
    
    @Query("SELECT r.carId FROM Reservation r WHERE r.startDate < :endDate AND r.endDate > :startDate AND r.reservationStatus = 'IN_PROGRESS'")
    public List<Long> findBookedCarIds(LocalDate startDate, LocalDate endDate);
}
