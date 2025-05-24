package com.hexaware.rentalservice.dto;

import java.time.LocalDate;

public class ReservationDTO {
	
	 private Long reservationId;
	 private LocalDate startDate;
	 private LocalDate endDate;
	 private String reservationStatus;
	 
	 private Long customerId;  
	 private Long carId;
	 
	 public ReservationDTO()
	 {
		 
	 }

	public ReservationDTO(Long reservationId, LocalDate startDate, LocalDate endDate, String reservationStatus,
			Long customerId, Long carId) {
		super();
		this.reservationId = reservationId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reservationStatus = reservationStatus;
		this.customerId = customerId;
		this.carId = carId;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(String reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

}
