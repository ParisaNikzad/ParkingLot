package com.gojek.assignment.domain;

public class Car implements Vehicle {

	private String color;
	private String registrationNumber;

	public Car(String color, String registrationNumber) {
		this.color = color;
		this.registrationNumber = registrationNumber;
	}

	public String getColor() {
		return this.color;
	}

	public String getRegistrationNumber() {
		return this.registrationNumber;
	}

}
