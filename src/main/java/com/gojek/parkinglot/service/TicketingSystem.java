package com.gojek.parkinglot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gojek.parkinglot.domain.StatusResponse;
import com.gojek.parkinglot.domain.Vehicle;
import com.gojek.parkinglot.service.exceptions.ParkingLotException;

class TicketingSystem {
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
	static TicketingSystem createInstance(int numberOfSlots) {
		if (ticketingSystem == null) {
			ParkingLot parkingLot = ParkingLot.getInstance(numberOfSlots);
			ticketingSystem = new TicketingSystem(parkingLot);
		}
		return ticketingSystem;
	}

	/**
	 * 
	 * @return TicketingSystem instance
	 */
	static TicketingSystem getInstance() {
		if(ticketingSystem == null) {
			throw new IllegalStateException("Parking Lot is not initialized");
		}
		return ticketingSystem;
	}

	/**
	 * Parks a vehicle
	 * 
	 * @return slotNumber => slot number at which the vehicle needs to be parked
	 */
	int issueParkingTicket(Vehicle vehicle) {
		if (vehicle == null) {
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
		if (tickets.containsKey(slotNumber)) {
			parkingLot.emptySlot(slotNumber);
			tickets.remove(slotNumber);
			return;
		} else {
			throw new IllegalStateException("Ticket Not found");
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
		if (color == null) {
			throw new IllegalArgumentException("color cannot be null");
		}
		List<String> registrationNumbers = new ArrayList<String>();
		for (Ticket ticket : tickets.values()) {
			if (color.equals(ticket.vehicle.getColor())) {
				registrationNumbers.add(ticket.vehicle.getRegistrationNumber());
			}
		}
		return registrationNumbers;
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
		if (registrationNumber == null) {
			throw new IllegalArgumentException("registrationNumber cannot be null");
		}
		for (Ticket ticket : tickets.values()) {
			if (registrationNumber.equals(ticket.vehicle.getRegistrationNumber())) {
				return ticket.slotNumber;
			}
		}

		throw new ParkingLotException("Not found");
	}

	/**
	 * returns all the slot numbers of the vehicles with the given color
	 * 
	 * @param color => Color of the Vehicle
	 * @return List of all the slot numbers of the vehicles with the given color
	 */
	List<Integer> getSlotNumbersFromColor(String color) {
		if (color == null) {
			throw new IllegalArgumentException("color cannot be null");
		}
		List<Integer> registrationNumbers = new ArrayList<Integer>();
		for (Ticket ticket : tickets.values()) {
			if (color.equals(ticket.vehicle.getColor())) {
				registrationNumbers.add(ticket.slotNumber);
			}
		}
		return registrationNumbers;
	}

	/**
	 * returns the status of the ticketing system, a list of all the tickets
	 * converted to status objects
	 * 
	 * @return List of StatusResponse => List of (slotNumber, registrationNumber,
	 *         color)
	 */
	List<StatusResponse> getStatus() {
		List<StatusResponse> statusResponseList = new ArrayList<StatusResponse>();
		for (Ticket ticket : tickets.values()) {
			statusResponseList.add(new StatusResponse(ticket.slotNumber, ticket.vehicle.getRegistrationNumber(),
					ticket.vehicle.getColor()));
		}
		return statusResponseList;
	}
	
	/**
	 * Ticketing System issues a ticket => an object known only to Ticketing System
	 *
	 */
	private class Ticket {
		int slotNumber;
		Vehicle vehicle;

		Ticket(int slotNumber, Vehicle vehicle) {
			this.slotNumber = slotNumber;
			this.vehicle = vehicle;
		}
	}
}
