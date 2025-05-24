package com.hexaware.vehicleservice.dto;

public class CarDTO {
	
	private Long carId;
    private String carName;
    private int year;
    private String make;
    private String carStatus;
    private String location;
    private int passengerCapacity;
    private double pricePerDay;
    
    public CarDTO()
    {
    	
    }

	public CarDTO(Long carId, String carName, int year, String make, String carStatus, String location, int passengerCapacity,
			double pricePerDay) {
		super();
		this.carId = carId;
		this.carName = carName;
		this.year = year;
		this.make = make;
		this.carStatus = carStatus;
		this.location = location;
		this.passengerCapacity = passengerCapacity;
		this.pricePerDay = pricePerDay;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(String carStatus) {
		this.carStatus = carStatus;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getPassengerCapacity() {
		return passengerCapacity;
	}

	public void setPassengerCapacity(int passengerCapacity) {
		this.passengerCapacity = passengerCapacity;
	}

	public double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

}
