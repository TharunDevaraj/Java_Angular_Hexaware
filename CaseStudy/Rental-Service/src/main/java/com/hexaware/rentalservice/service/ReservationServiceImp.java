package com.hexaware.rentalservice.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.rentalservice.dto.ReservationDTO;
import com.hexaware.rentalservice.entity.Reservation;
import com.hexaware.rentalservice.repository.ReservationRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReservationServiceImp implements IReservationService{

	@Autowired
	ReservationRepository reservationRepository;

	@Override
	public Reservation createReservation(ReservationDTO reservationDTO) {
		log.info("Creating reservation for customerId={}, carId={} from {} to {}", 
                reservationDTO.getCustomerId(), reservationDTO.getCarId(), 
                reservationDTO.getStartDate(), reservationDTO.getEndDate());
		
		if (reservationDTO.getEndDate().isBefore(reservationDTO.getStartDate())) {
			log.warn("End date must be after start date");
		    throw new IllegalArgumentException("End date must be after start date");
		}

		Reservation reservation = new Reservation();
        
        reservation.setStartDate(reservationDTO.getStartDate());
        reservation.setEndDate(reservationDTO.getEndDate());
        reservation.setCustomerId(reservationDTO.getCustomerId());
        reservation.setCarId(reservationDTO.getCarId());
        reservation.setReservationStatus("Reserved"); 
        
        Reservation savedReservation = reservationRepository.save(reservation);
        log.debug("Reservation saved successfully: {}", savedReservation);
        return savedReservation;
	}

	@Override
	public List<Reservation> getAllReservations() {
		log.info("Fetching all reservations");
        List<Reservation> reservations = reservationRepository.findAll();
        log.debug("Total reservations fetched: {}", reservations.size());
        return reservations;
	}

	@Override
	public ReservationDTO getReservationById(Long reservationId) {
		
		log.info("Fetching reservation with id={} ", reservationId);
		
		Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation == null) {
        	log.warn("Reservation with id={} not found", reservationId);
        	throw new RuntimeException("Reservation not found");
        }
        return mapToDTO(reservation);
	}

	@Override
	public List<Reservation> getReservationsByCustomerId(Long customerId) {
		log.info("Fetching reservations for customerId={}", customerId);
        List<Reservation> reservations = reservationRepository.findByCustomerId(customerId);
        log.debug("Found {} reservations for customerId={}", reservations.size(), customerId);
        return reservations;
     }

	@Override
	public List<Reservation> getReservationsByCarId(Long carId) {
		log.info("Fetching reservations for carId={}", carId);
        List<Reservation> reservations = reservationRepository.findByCarId(carId);
        log.debug("Found {} reservations for carId={}", reservations.size(), carId);
        return reservations;
	}

	@Override
	public Reservation updateReservation(ReservationDTO updatedReservationDTO) {
		log.info("Updating reservation with id={} ",updatedReservationDTO.getReservationId());
		Reservation existingReservation = reservationRepository.findById(updatedReservationDTO.getReservationId()).orElse(null);
        if (existingReservation == null) {
        	log.warn("Reservation with id={} not found", updatedReservationDTO.getReservationId());
        	throw new RuntimeException("Reservation not found");
        }
           
        existingReservation.setStartDate(updatedReservationDTO.getStartDate());
        existingReservation.setEndDate(updatedReservationDTO.getEndDate());
        existingReservation.setCustomerId(updatedReservationDTO.getCustomerId());
        existingReservation.setCarId(updatedReservationDTO.getCarId());
        log.debug("Reservation updated successfully: {}", existingReservation);
        return reservationRepository.save(existingReservation);
        
	}

	@Override
	public void cancelReservationById(Long id) {
		log.info("Cancelling reservation with id={} ", id);
		Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation != null) {
            reservation.setReservationStatus("Cancelled");
            reservationRepository.save(reservation);
            log.debug("Reservation with id={} successfully cancelled", id);
        }
        else
        {
        	log.warn("Attempted to cancel non-existing reservation with id={} ", id);
        	throw new RuntimeException("Reservation not found");
        }
		
	}
	
	private ReservationDTO mapToDTO(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setReservationId(reservation.getReservationId());
        dto.setStartDate(reservation.getStartDate());
        dto.setEndDate(reservation.getEndDate());
        dto.setCustomerId(reservation.getCustomerId());
        dto.setCarId(reservation.getCarId());
        dto.setReservationStatus(reservation.getReservationStatus());
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
