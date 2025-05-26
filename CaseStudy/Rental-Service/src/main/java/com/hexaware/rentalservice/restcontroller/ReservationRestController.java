package com.hexaware.rentalservice.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.rentalservice.dto.ReservationDTO;
import com.hexaware.rentalservice.entity.Reservation;
import com.hexaware.rentalservice.service.IReservationService;

@RestController
@RequestMapping("api/reservation")
public class ReservationRestController {

	@Autowired
    IReservationService reservationService;

    @PostMapping("/create")
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationDTO reservationDTO) {
        Reservation reservation = reservationService.createReservation(reservationDTO);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Reservation> updateReservation(@RequestBody ReservationDTO reservationDTO) {
        Reservation updated = reservationService.updateReservation(reservationDTO);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/cancel/{reservationId}")
    public ResponseEntity<String> cancelReservation(@PathVariable Long reservationId) {
        reservationService.cancelReservationById(reservationId);
        return new ResponseEntity<>("Reservation cancelled successfully", HttpStatus.OK);
    }

    @GetMapping("/get/{reservationId}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long reservationId) {
        ReservationDTO dto = reservationService.getReservationById(reservationId);
        if (dto != null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Reservation>> getReservationsByCustomerId(@PathVariable Long customerId) {
        List<Reservation> reservations = reservationService.getReservationsByCustomerId(customerId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<List<Reservation>> getReservationsByCarId(@PathVariable Long carId) {
        List<Reservation> reservations = reservationService.getReservationsByCarId(carId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return new ResponseEntity<>(reservationService.getAllReservations(), HttpStatus.OK);
    }
}
