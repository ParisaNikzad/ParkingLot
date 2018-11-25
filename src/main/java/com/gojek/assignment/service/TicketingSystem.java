package com.gojek.assignment.service;

import java.util.List;

import com.gojek.assignment.domain.Vehicle;

public class TicketingSystem {
	private static TicketingSystem ticketingSystem;
	private ParkingLot parkingLot;

	/**
	 * VisibleForTesting(otherwise = PRIVATE)
	 */
	TicketingSystem(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}

	/**
	 * Singleton Class => Returns a single instance of the class
	 * 
	 * @param numberOfSlots => Number of slots of the parking lot that this
	 *                      ticketing system is managing
	 * @return TicketingSystem instance
	 */
	static TicketingSystem getInstance(int numberOfSlots) {
		if (ticketingSystem == null) {
			ParkingLot parkingLot = ParkingLot.getInstance(numberOfSlots);
			ticketingSystem = new TicketingSystem(parkingLot);
		}
		return ticketingSystem;
	}

	/**
	 * Parks a vehicle
	 * 
	 * @return slotNumber => slot number at which the vehicle needs to be parked
	 */
	int issueParkingTicket(Vehicle vehicle) {
		throw new RuntimeException("Not yet defined");
	}

	/**
	 * Exits a vehicle from the parking lot => TODO:// Better Naming ??
	 * 
	 * @param vehicle
	 * @return slotNumber => the slot from the car has exited.
	 */
	int exitVehicle(Vehicle vehicle) {
		throw new RuntimeException("Not yet defined");
	}

	/**
	 * returns all the registration numbers of the vehicles with the given color
	 * 
	 * @param color => Color of the Vehicle
	 * @return List of all the registration numbers of the vehicles with the given
	 *         color
	 */
	List<String> getRegistrationNumbersFromColor(String color) {
		throw new RuntimeException("Not yet defined");
	}

	/**
	 * returns the slot number at which the Vehicle with given registrationNumber is
	 * parked
	 * 
	 * @param registrationNumber => Registration Number of the Vehicle
	 * @return slot number at which the Vehicle with given registrationNumber is
	 *         parked
	 */
	int getSlotNumberFromRegistrationNumber(String registrationNumber) {
		throw new RuntimeException("Not yet defined");
	}

	/**
	 * returns all the slot numbers of the vehicles with the given color
	 * 
	 * @param color => Color of the Vehicle
	 * @return List of all the slot numbers of the vehicles with the given color
	 */
	List<String> getSlotNumbersFromColor(String color) {
		throw new RuntimeException("Not yet defined");
	}
}
