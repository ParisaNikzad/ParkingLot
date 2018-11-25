package com.gojek.assignment.service;

import org.junit.Assert;
import org.junit.Test;

import com.gojek.assignment.domain.Car;

public class TicketingSystemTest {
	
	@Test
	public void integrationTest() {
		TicketingSystem ticketingSystem = TicketingSystem.getInstance(6);
		
		int slot1 = ticketingSystem.issueParkingTicket(new Car("KA-01-HH-1234", "White"));
		Assert.assertEquals(1, slot1);
		
		int slot2 = ticketingSystem.issueParkingTicket(new Car("KA-01-HH-9999", "White"));
		Assert.assertEquals(2, slot2);
		
		int slot3 = ticketingSystem.issueParkingTicket(new Car("KA-01-BB-0001", "Black"));
		Assert.assertEquals(3, slot3);
		
		int slot4 = ticketingSystem.issueParkingTicket(new Car("KA-01-HH-7777", "Red"));
		Assert.assertEquals(4, slot4);
		
		int slot5 = ticketingSystem.issueParkingTicket(new Car("KA-01-HH-2701", "Blue"));
		Assert.assertEquals(5, slot5);
		
		int slot6 = ticketingSystem.issueParkingTicket(new Car("KA-01-HH-3141", "Black"));
		Assert.assertEquals(6, slot6);
		
		ticketingSystem.exitVehicle(4);
		
		int slot7 = ticketingSystem.issueParkingTicket(new Car("KA-01-P-333", "White"));
		Assert.assertEquals(4, slot7);
		
		try {
			ticketingSystem.issueParkingTicket(new Car("DL-12-AA-9999", "White"));
			Assert.assertTrue("parking lot is full", false);
		} catch (Exception e) {
			Assert.assertEquals("", "Sorry, parking lot is full", e.getMessage());
		}
		
	}
	
	@Test
	public void issueParkingTicketWithValidVehicle() {
		TicketingSystem ticketingSystem = new TicketingSystem(new FakeParkingLot(3));
		
		int slot1 = ticketingSystem.issueParkingTicket(new Car("", ""));
		Assert.assertEquals(3, slot1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void issueParkingTicketWithNullVehicle() {
		TicketingSystem ticketingSystem = new TicketingSystem(new FakeParkingLot(3));
		int slot1 = ticketingSystem.issueParkingTicket(null);
	}

	@Test
	public void exitVehicleWithValidRegistrationNumber() {
		FakeParkingLot fakeParkingLot = new FakeParkingLot(3);
		TicketingSystem ticketingSystem = new TicketingSystem(fakeParkingLot);
		ticketingSystem.issueParkingTicket(new Car("KA-01-HH-3141", "White"));
		
		ticketingSystem.exitVehicle(3);
		Assert.assertEquals(3, fakeParkingLot.emptySlotNumber);
	}

	@Test
	public void exitVehicleWithInvalidSlotNumber() {
		FakeParkingLot fakeParkingLot = new FakeParkingLot(3);
		TicketingSystem ticketingSystem = new TicketingSystem(fakeParkingLot);
		
		try {
			ticketingSystem.exitVehicle(3);
			Assert.assertTrue("should throw ticket not found exception", false);
		} catch (Exception e) {
			Assert.assertEquals("", "Ticket Not found", e.getMessage());
		}
	}

	@Test
	public void getRegistrationNumbersFromColor() {
		
	}

	@Test
	public void getSlotNumberFromRegistrationNumberWithValidRegistrationNumber() {

	}

	@Test
	public void getSlotNumberFromRegistrationNumberWithInvalidRegistrationNumber() {

	}

	@Test
	public void getSlotNumbersFromColor() {

	}

	private class FakeParkingLot extends ParkingLot {

		private int nextAvailableSlotNumber;
		private int emptySlotNumber;

		FakeParkingLot(int slotNumber) {
			super(1);
			this.nextAvailableSlotNumber = slotNumber;
			this.emptySlotNumber = slotNumber;
		}

		@Override
		int fillAvailableSlot() {
			return nextAvailableSlotNumber;
		}

		@Override
		void emptySlot(int slotNumber) {
			this.emptySlotNumber = slotNumber;
		}
	}
}
