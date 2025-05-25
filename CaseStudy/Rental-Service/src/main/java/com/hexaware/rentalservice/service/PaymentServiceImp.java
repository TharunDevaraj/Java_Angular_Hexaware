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

@Service
public class PaymentServiceImp implements IPaymentService{
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	ReservationRepository reservationRepository;

	@Override
	public Payment makePayment(PaymentDTO paymentDTO) {
		
		Payment payment = new Payment();
        
        payment.setPaymentType(paymentDTO.getPaymentType());
        payment.setAmount(paymentDTO.getAmount());
        payment.setReservationId(paymentDTO.getReservationId());
        payment.setPaymentDate(paymentDTO.getPaymentDate());
        
        return paymentRepository.save(payment);
	}

	@Override
	public PaymentDTO getPaymentById(Long paymentId) {
		
		Payment payment = paymentRepository.findById(paymentId).orElse(null);
        if (payment != null) {
            return mapToDTO(payment);
        }
        return null;
	}

	@Override
	public List<Payment> getAllPayments() {
		
		return paymentRepository.findAll();
	}

	@Override
	public List<Payment> getPaymentsByReservationId(Long reservationId) {
		
		return paymentRepository.findByReservationId(reservationId);
	}

	@Override
	public List<Payment> getPaymentsByCustomerId(Long customerId) {
		
		List<Reservation> reservations = reservationRepository.findByCustomerId(customerId);
	    List<Payment> customerPayments = new ArrayList<>();

	    for (Reservation reservation : reservations) {
	        List<Payment> payments = getPaymentsByReservationId(reservation.getReservationId());
	        customerPayments.addAll(payments);
	    }

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
