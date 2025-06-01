package com.hexaware.rentalservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.rentalservice.dto.PaymentDTO;
import com.hexaware.rentalservice.entity.Payment;
import com.hexaware.rentalservice.entity.Reservation;
import com.hexaware.rentalservice.repository.PaymentRepository;
import com.hexaware.rentalservice.repository.ReservationRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentServiceImp implements IPaymentService{
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	ReservationRepository reservationRepository;

	@Override
	public Payment makePayment(PaymentDTO paymentDTO) {
		log.info("Creating payment for reservationId={}, amount={} on {}", 
                paymentDTO.getReservationId(), paymentDTO.getAmount(), paymentDTO.getPaymentDate());
		Payment payment = new Payment();
        
        payment.setPaymentType(paymentDTO.getPaymentType());
        payment.setAmount(paymentDTO.getAmount());
        payment.setReservationId(paymentDTO.getReservationId());
        payment.setPaymentDate(paymentDTO.getPaymentDate());
        Payment savedPayment = paymentRepository.save(payment);
        log.debug("Payment saved successfully: {}", savedPayment);
        return savedPayment;
	}

	@Override
	public PaymentDTO getPaymentById(Long paymentId) {
		log.info("Fetching payment with paymentId={}", paymentId);
		Payment payment = paymentRepository.findById(paymentId).orElse(null);
        if (payment != null) {
            return mapToDTO(payment);
        }
        log.warn("Payment with paymentId={} not found",paymentId);
        return null;
	}

	@Override
	public List<Payment> getAllPayments() {
		log.info("Fetching all payments");
        List<Payment> payments = paymentRepository.findAll();
        log.debug("Total payments fetched: {}", payments.size());
        return payments;
	}

	@Override
	public List<Payment> getPaymentsByReservationId(Long reservationId) {
		log.info("Fetching payments for reservationId={}", reservationId);
        List<Payment> payments = paymentRepository.findByReservationId(reservationId);
        log.debug("Found {} payments for reservationId={}", payments.size(), reservationId);
        return payments;
	}

	@Override
	public List<Payment> getPaymentsByCustomerId(Long customerId) {
		log.info("Fetching payments for customerId={}", customerId);
		List<Reservation> reservations = reservationRepository.findByCustomerId(customerId);
	    List<Payment> customerPayments = new ArrayList<>();

	    for (Reservation reservation : reservations) {
	        List<Payment> payments = getPaymentsByReservationId(reservation.getReservationId());
	        customerPayments.addAll(payments);
	    }
	    log.debug("Found {} payments for customerId={}", customerPayments.size(), customerId);
	    return customerPayments;
	}
	
	private PaymentDTO mapToDTO(Payment payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setPaymentId(payment.getPaymentId());
        dto.setPaymentType(payment.getPaymentType());
        dto.setAmount(payment.getAmount());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setReservationId(payment.getReservationId());
        return dto;
    }

}
