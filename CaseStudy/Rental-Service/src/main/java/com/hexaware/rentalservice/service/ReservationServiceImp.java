package com.hexaware.rentalservice.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.rentalservice.dto.ReservationDTO;
import com.hexaware.rentalservice.entity.Reservation;
import com.hexaware.rentalservice.repository.ReservationRepository;

@Service
public class ReservationServiceImp implements IReservationService{

	
	@Autowired
	ReservationRepository reservationRepository;
	@Override
	public Reservation createReservation(ReservationDTO reservationDTO) {
		
		Reservation reservation = new Reservation();
        
        reservation.setStartDate(reservationDTO.getStartDate());
        reservation.setEndDate(reservationDTO.getEndDate());
        reservation.setCustomerId(reservationDTO.getCustomerId());
        reservation.setCarId(reservationDTO.getCarId());
        reservation.setReservationStatus("Reserved"); 
        return reservationRepository.save(reservation);
	}

	@Override
	public List<Reservation> getAllReservations() {
		
		return reservationRepository.findAll();
	}

	@Override
	public ReservationDTO getReservationById(Long reservationId) {
		
		Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation == null) {
            return null;
        }
        return mapToDTO(reservation);
	}

	@Override
	public List<Reservation> getReservationsByCustomerId(Long customerId) {
		
		return reservationRepository.findByCustomerId(customerId);
	}

	@Override
	public List<Reservation> getReservationsByCarId(Long carId) {
		
		return reservationRepository.findByCarId(carId);
	}

	@Override
	public Reservation updateReservation(ReservationDTO updatedReservationDTO) {
		
		Reservation existingReservation = reservationRepository.findById(updatedReservationDTO.getReservationId()).orElse(null);
        if (existingReservation == null) {
        	return null;
        }
           
        existingReservation.setStartDate(updatedReservationDTO.getStartDate());
        existingReservation.setEndDate(updatedReservationDTO.getEndDate());
        existingReservation.setReservationStatus(updatedReservationDTO.getReservationStatus());
        existingReservation.setCustomerId(updatedReservationDTO.getCustomerId());
        existingReservation.setCarId(updatedReservationDTO.getCarId());
        return reservationRepository.save(existingReservation);
        
	}

	@Override
	public void cancelReservationById(Long id) {
		
		Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation != null) {
            reservation.setReservationStatus("Cancelled");
            reservationRepository.save(reservation);
        }
		
	}
	
	private ReservationDTO mapToDTO(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setReservationId(reservation.getReservationId());
        dto.setStartDate(reservation.getStartDate());
        dto.setEndDate(reservation.getEndDate());
        dto.setReservationStatus(reservation.getReservationStatus());
        dto.setCustomerId(reservation.getCustomerId());
        dto.setCarId(reservation.getCarId());
        return dto;
    }

	@Override
	public List<Long> findBookedCars(LocalDate startDate, LocalDate endDate) {
		
		return reservationRepository.findBookedCarIds(startDate, endDate);
	}
	
	 @Override
	    public Reservation checkIn(Long reservationId) {
	        Reservation reservation = reservationRepository.findById(reservationId)
	                .orElseThrow(() -> new RuntimeException("Reservation not found"));

	        reservation.setReservationStatus("Reserved");
	        reservation.setCheckInTime(LocalDateTime.now());

	        return reservationRepository.save(reservation);
	    }

	    @Override
	    public Reservation checkOut(Long reservationId) {
	        Reservation reservation = reservationRepository.findById(reservationId)
	                .orElseThrow(() -> new RuntimeException("Reservation not found"));

	        reservation.setReservationStatus("Completed");
	        reservation.setCheckOutTime(LocalDateTime.now());

	        return reservationRepository.save(reservation);
	    }
	

}
