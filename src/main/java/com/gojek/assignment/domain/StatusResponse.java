package com.gojek.assignment.domain;

public class StatusResponse {
	private int slotNumber;
	private String registrationNumber;
	private String color;

	public StatusResponse(int slotNumber, String registrationNumber, String color) {
		this.slotNumber = slotNumber;
		this.registrationNumber = registrationNumber;
		this.color = color;
	}

	public int getSlotNumber() {
		return slotNumber;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public String getColor() {
		return color;
	}

	@Override
	public String toString() {
		return slotNumber + "\t" + registrationNumber + "\t" + color;
	}

}
