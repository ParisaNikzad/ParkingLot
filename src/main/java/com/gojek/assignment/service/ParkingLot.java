package com.gojek.assignment.service;

class ParkingLot {
	private ParkingLot parkingLot;

	private ParkingLot() {

	}

	/**
	 * Singleton Class => Returns a single instance of the class
	 * 
	 * @param numberOfSlots => number of slots in the parking lot
	 * @return parkingLot instance
	 */
	ParkingLot getInstance(int numberOfSlots) {
		if (parkingLot == null) {
			parkingLot = new ParkingLot();
		}
		return parkingLot;
	}

	/**
	 * Finds the next available slot and marks it unavailable
	 * 
	 * @return slot number which was marked unavailable
	 */
	int fillAvailableSlot() {
		throw new RuntimeException("Not yet defined");
	}

	/**
	 * Empties the Slot => marks the slot available
	 * 
	 * @param slotNumber => the slot number to be made empty
	 */
	void emptySlot(int slotNumber) {
		throw new RuntimeException("Not yet defined");
	}
}
