package com.hexaware.rentalservice.service;

import java.util.List;

import com.hexaware.rentalservice.dto.ReservationDTO;
import com.hexaware.rentalservice.entity.Reservation;

public interface IReservationService {
	
	public Reservation createReservation(ReservationDTO reservationDTO);
	
	public List<Reservation> getAllReservations();
	
	public ReservationDTO getReservationById(Long reservationId);
	
	public List<Reservation> getReservationsByCustomerId(Long customerId);
	
	public List<Reservation> getReservationsByCarId(Long carId);
	
	public Reservation updateReservation(ReservationDTO updatedReservationDTO);
	
	public void cancelReservationById(Long id);

}
