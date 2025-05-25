package com.hexaware.rentalservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.rentalservice.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

	List<Payment> findByReservationId(Long reservationId);
}
