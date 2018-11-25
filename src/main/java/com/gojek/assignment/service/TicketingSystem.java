package com.gojek.assignment.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gojek.assignment.domain.Vehicle;

public class TicketingSystem {
	private static TicketingSystem ticketingSystem;
	private ParkingLot parkingLot;
	private Map<Integer, Ticket> tickets;

	/**
	 * VisibleForTesting(otherwise = PRIVATE)
	 */
	TicketingSystem(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
		tickets = new HashMap<Integer, Ticket>();
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
	int issueParkingTicket(Vehicle vehicle) throws RuntimeException{
		if(vehicle == null) {
			throw new IllegalArgumentException("Vehicle cannot be null");
		}
		int assignedSlotNumber = parkingLot.fillAvailableSlot();
		Ticket ticket = new Ticket(assignedSlotNumber, vehicle);
		tickets.put(assignedSlotNumber, ticket);
		return assignedSlotNumber;
	}

	/**
	 * Exits a vehicle from the parking lot => TODO:// Better Naming ??
	 * 
	 * @param registrationNumber
	 * @return slotNumber => the slot from the car has exited.
	 */
	void exitVehicle(int slotNumber) {
		if(tickets.containsKey(slotNumber)) {
			parkingLot.emptySlot(slotNumber);
			tickets.remove(slotNumber);
			return;
		} else {
			throw new RuntimeException("Ticket Not found");
		}
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

	private class Ticket {
		int slotNumber;
		Vehicle vehicle;

		Ticket(int slotNumber, Vehicle vehicle) {
			this.slotNumber = slotNumber;
			this.vehicle = vehicle;
		}
	}
}
