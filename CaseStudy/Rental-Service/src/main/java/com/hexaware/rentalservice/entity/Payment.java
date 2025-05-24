package com.hexaware.rentalservice.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Payment {
	
	@Id
	@GeneratedValue
	private Long paymentId;
	private String paymentType;
	private LocalDate paymentDate;
	private double amount;
	
	private Long reservationId;
	
	public Payment()
	{
		
	}

	public Payment(Long paymentId, String paymentType, LocalDate paymentDate, double amount, Long reservationId) {
		super();
		this.paymentId = paymentId;
		this.paymentType = paymentType;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.reservationId = reservationId;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", paymentType=" + paymentType + ", paymentDate=" + paymentDate
				+ ", amount=" + amount + ", reservationId=" + reservationId + "]";
	}
	

}
