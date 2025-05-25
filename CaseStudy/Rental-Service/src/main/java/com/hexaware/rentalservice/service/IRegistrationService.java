package com.hexaware.rentalservice.service;

import java.util.List;

import com.hexaware.rentalservice.entity.Reservation;

public interface IRegistrationService {
	
	Reservation createReservation(Reservation reservation);
	
	List<Reservation> getAllReservations();
	
	Reservation getReservationById(Long reservationId);
	
	List<Reservation> getReservationsByCustomerId(Long customerId);
	
	List<Reservation> getReservationsByCarId(Long carId);
	
	Reservation updateReservation(Reservation updatedReservation);
	
	void cancelReservationById(Long id);

}
