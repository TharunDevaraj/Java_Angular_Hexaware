package com.hexaware.rentalservice.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.hexaware.rentalservice.repository.PaymentRepository;
import com.hexaware.rentalservice.repository.ReservationRepository;

public class ReportServiceImp implements IReportService{

	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Override
	public double getTotalRevenue() {
		
		return paymentRepository.findTotalRevenue();
	}

	@Override
	public long getTotalReservations() {
		
		return reservationRepository.count();
	}

}
