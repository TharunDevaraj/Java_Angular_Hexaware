package com.hexaware.rentalservice.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

public class PaymentDTO {
	
	private Long paymentId;
	
	@NotBlank(message = "Payment type is required")
	private String paymentType;
	
	@NotNull(message = "Payment date is required")
    @PastOrPresent(message = "Payment date cannot be in the future")
	private LocalDate paymentDate;
	
	@Positive(message = "Amount must be greater than zero")
	private double amount;
	
	@NotNull(message = "Reservation ID is required")
	private Long reservationId;
	
	public PaymentDTO()
	{
		
	}

	public PaymentDTO(Long paymentId, String paymentType, LocalDate paymentDate, double amount, Long reservationId) {
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

}
