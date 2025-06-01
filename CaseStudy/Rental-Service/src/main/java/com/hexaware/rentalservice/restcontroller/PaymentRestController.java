package com.hexaware.rentalservice.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.rentalservice.dto.PaymentDTO;
import com.hexaware.rentalservice.entity.Payment;
import com.hexaware.rentalservice.service.IPaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentRestController {

	@Autowired
    IPaymentService paymentService;

    @PostMapping("/make")
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<Payment> makePayment(@RequestBody PaymentDTO paymentDTO) {
        Payment payment = paymentService.makePayment(paymentDTO);
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

    @GetMapping("/get/{paymentId}")
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long paymentId) {
        PaymentDTO dto = paymentService.getPaymentById(paymentId);
        if (dto != null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @GetMapping("/reservation/{reservationId}")
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<List<Payment>> getPaymentsByReservationId(@PathVariable Long reservationId) {
        List<Payment> payments = paymentService.getPaymentsByReservationId(reservationId);
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<List<Payment>> getPaymentsByCustomerId(@PathVariable Long customerId) {
        List<Payment> payments = paymentService.getPaymentsByCustomerId(customerId);
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
}
