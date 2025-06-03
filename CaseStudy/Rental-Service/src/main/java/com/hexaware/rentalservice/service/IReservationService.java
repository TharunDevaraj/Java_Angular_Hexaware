package com.hexaware.rentalservice.service;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.rentalservice.dto.ReservationDTO;
import com.hexaware.rentalservice.entity.Reservation;
import com.hexaware.rentalservice.exception.GlobalExceptionHandler;

public interface IReservationService {
	
	public ReservationDTO createReservation(ReservationDTO reservationDTO);
	
	public List<ReservationDTO> getAllReservations();
	
	public ReservationDTO getReservationById(Long reservationId);
	
	public List<ReservationDTO> getReservationsByCustomerId(Long customerId);
	
	public List<ReservationDTO> getReservationsByCarId(Long carId);
	
	public ReservationDTO updateReservation(ReservationDTO updatedReservationDTO);
	
	public void cancelReservationById(Long id);
	
	public List<Long> findBookedCars(LocalDate startDate,LocalDate endDate);
	
	public ReservationDTO checkIn(Long reservationId);

    public ReservationDTO checkOut(Long reservationId);
}
