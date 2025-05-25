package com.hexaware.rentalservice.service;

import java.util.List;

import com.hexaware.rentalservice.dto.PaymentDTO;
import com.hexaware.rentalservice.entity.Payment;

public interface IPaymentService {
	
	public Payment makePayment(PaymentDTO paymentDTO);
	
	public PaymentDTO getPaymentById(Long paymentId);
	
	public List<Payment> getAllPayments();
	
	public List<Payment> getPaymentsByReservationId(Long reservationId);
	
	public List<Payment> getPaymentsByCustomerId(Long customerId); 


}
