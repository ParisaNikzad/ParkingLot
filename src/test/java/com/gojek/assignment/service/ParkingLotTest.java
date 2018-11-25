package com.gojek.assignment.service;

import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {

	@Test
	public void fillAvailableSlotWhenSlotIsAvailable() {
		ParkingLot parkingLot = new ParkingLot(2);

		int slot1 = parkingLot.fillAvailableSlot();
		Assert.assertEquals(1, slot1);

		int slot2 = parkingLot.fillAvailableSlot();
		Assert.assertEquals(2, slot2);
	}

	@Test
	public void fillAvailableSlotWhenNoSlotIsAvailable() {
		ParkingLot parkingLot = new ParkingLot(1);

		int slot = parkingLot.fillAvailableSlot();
		Assert.assertEquals(1, slot);

		try {
			parkingLot.fillAvailableSlot();
		} catch (Exception e) {
			String message = e.getMessage();
			Assert.assertEquals("", "Sorry, parking lot is full", message); // TODO: Add proper error message
		}
	}

	@Test
	public void emptySlotWithValidSlotNumber() {
		ParkingLot parkingLot = new ParkingLot(3);

		int slot = parkingLot.fillAvailableSlot();
		Assert.assertEquals(1, slot);

		int slot2 = parkingLot.fillAvailableSlot();
		Assert.assertEquals(2, slot2);

		int slot3 = parkingLot.fillAvailableSlot();
		Assert.assertEquals(3, slot3);

		parkingLot.emptySlot(2);
		int slot4 = parkingLot.fillAvailableSlot();
		Assert.assertEquals(2, slot4);

		parkingLot.emptySlot(1);
		int slot5 = parkingLot.fillAvailableSlot();
		Assert.assertEquals(1, slot5);
	}

	@Test
	public void emptySlotWithInvalidSlotNumber() {
		ParkingLot parkingLot = new ParkingLot(2);

		int slot = parkingLot.fillAvailableSlot();
		Assert.assertEquals(1, slot);

		int slot2 = parkingLot.fillAvailableSlot();
		Assert.assertEquals(2, slot2);

		try {
			parkingLot.emptySlot(3);
		} catch (Exception e) {
			String message = e.getMessage();
			Assert.assertEquals("", "The slot is not filled", message); // TODO: Add proper error message
		}
	}

	@Test
	public void emptySlotWhichIsAlreadyEmpty() {
		ParkingLot parkingLot = new ParkingLot(2);

		int slot = parkingLot.fillAvailableSlot();
		Assert.assertEquals(1, slot);

		try {
			parkingLot.emptySlot(2);
		} catch (Exception e) {
			String message = e.getMessage();
			Assert.assertEquals("", "The slot is already empty", message); // TODO: Add proper error message
		}
	}

}
