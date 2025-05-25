package com.hexaware.rentalservice.service;

import java.util.List;

import com.hexaware.rentalservice.entity.Payment;

public interface IPaymentService {
	
	Payment makePayment(Payment payment);
	
	Payment getPaymentById(Long paymentId);
	
	List<Payment> getAllPayments();
	
	List<Payment> getByReservationId(Long reservationId);
	
	List<Payment> getPaymentsByCustomerId(Long customerId); 


}
