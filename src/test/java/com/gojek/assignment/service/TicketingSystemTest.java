package com.gojek.assignment.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.gojek.assignment.domain.Car;
import com.gojek.assignment.domain.StatusResponse;

public class TicketingSystemTest {

	@Test
	public void integrationTest() {
		TicketingSystem ticketingSystem = TicketingSystem.createInstance(6);

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

		List<String> registrationNumbers = ticketingSystem.getRegistrationNumbersFromColor("White");
		Assert.assertEquals(3, registrationNumbers.size());

		List<Integer> slotNumbers = ticketingSystem.getSlotNumbersFromColor("White");
		Assert.assertEquals(3, slotNumbers.size());

		int slotNumber = ticketingSystem.getSlotNumberFromRegistrationNumber("KA-01-HH-3141");
		Assert.assertEquals(6, slotNumber);

		try {
			int slotNumber2 = ticketingSystem.getSlotNumberFromRegistrationNumber("MH-04-AY-1111");
			Assert.assertTrue("should throw not found exception", true);
		} catch (Exception e) {
			Assert.assertEquals("", "Not found", e.getMessage());
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
		TicketingSystem ticketingSystem = new TicketingSystem(new ParkingLot(5));

		ticketingSystem.issueParkingTicket(new Car("KA-01-HH-1234", "White"));
		ticketingSystem.issueParkingTicket(new Car("KA-01-HH-9999", "White"));
		ticketingSystem.issueParkingTicket(new Car("KA-01-BB-0001", "Black"));
		ticketingSystem.issueParkingTicket(new Car("KA-01-HH-7777", "Blue"));
		ticketingSystem.issueParkingTicket(new Car("KA-01-HH-2701", "Blue"));

		// TODO: Assert Values
		List<String> registrationNumbers = ticketingSystem.getRegistrationNumbersFromColor("White");
		Assert.assertEquals(2, registrationNumbers.size());

		List<String> registrationNumbers2 = ticketingSystem.getRegistrationNumbersFromColor("Black");
		Assert.assertEquals(1, registrationNumbers2.size());

		List<String> registrationNumbers3 = ticketingSystem.getRegistrationNumbersFromColor("Blue");
		Assert.assertEquals(2, registrationNumbers3.size());

		List<String> registrationNumbers4 = ticketingSystem.getRegistrationNumbersFromColor("Green");
		Assert.assertEquals(0, registrationNumbers4.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void getRegistrationNumbersFromNullColor() {
		TicketingSystem ticketingSystem = new TicketingSystem(new ParkingLot(5));
		ticketingSystem.getRegistrationNumbersFromColor(null);
	}

	@Test
	public void getSlotNumberFromRegistrationNumberWithValidRegistrationNumber() {
		TicketingSystem ticketingSystem = new TicketingSystem(new ParkingLot(5));

		ticketingSystem.issueParkingTicket(new Car("KA-01-HH-1234", "White"));
		ticketingSystem.issueParkingTicket(new Car("KA-01-HH-9999", "White"));

		int slot1 = ticketingSystem.getSlotNumberFromRegistrationNumber("KA-01-HH-1234");
		Assert.assertEquals(1, slot1);

		int slot2 = ticketingSystem.getSlotNumberFromRegistrationNumber("KA-01-HH-9999");
		Assert.assertEquals(2, slot2);
	}

	@Test
	public void getSlotNumberFromRegistrationNumberWithInvalidRegistrationNumber() {
		TicketingSystem ticketingSystem = new TicketingSystem(new ParkingLot(5));

		try {
			ticketingSystem.getSlotNumberFromRegistrationNumber("KA-01-HH-9999");
			Assert.assertTrue("should throw not found exception", false);
		} catch (Exception e) {
			Assert.assertEquals("", "Not found", e.getMessage());
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void getSlotNumberFromRegistrationNumberWithNullRegistrationNumber() {
		TicketingSystem ticketingSystem = new TicketingSystem(new ParkingLot(5));
		ticketingSystem.getSlotNumberFromRegistrationNumber(null);
	}

	@Test
	public void getSlotNumbersFromColor() {
		TicketingSystem ticketingSystem = new TicketingSystem(new ParkingLot(5));

		ticketingSystem.issueParkingTicket(new Car("KA-01-HH-1234", "White"));
		ticketingSystem.issueParkingTicket(new Car("KA-01-HH-9999", "White"));
		ticketingSystem.issueParkingTicket(new Car("KA-01-BB-0001", "Black"));
		ticketingSystem.issueParkingTicket(new Car("KA-01-HH-7777", "Blue"));
		ticketingSystem.issueParkingTicket(new Car("KA-01-HH-2701", "Blue"));

		// TODO: Assert Values
		List<Integer> slotNumbers = ticketingSystem.getSlotNumbersFromColor("White");
		Assert.assertEquals(2, slotNumbers.size());

		List<Integer> slotNumbers2 = ticketingSystem.getSlotNumbersFromColor("Black");
		Assert.assertEquals(1, slotNumbers2.size());

		List<Integer> slotNumbers3 = ticketingSystem.getSlotNumbersFromColor("Blue");
		Assert.assertEquals(2, slotNumbers3.size());

		List<Integer> slotNumbers4 = ticketingSystem.getSlotNumbersFromColor("Green");
		Assert.assertEquals(0, slotNumbers4.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void getSlotNumbersFromNullColor() {
		TicketingSystem ticketingSystem = new TicketingSystem(new ParkingLot(5));
		ticketingSystem.getSlotNumbersFromColor(null);
	}

	@Test
	public void getStatus() {
		TicketingSystem ticketingSystem = new TicketingSystem(new ParkingLot(1));
		ticketingSystem.issueParkingTicket(new Car("KA-01-HH-2701", "Blue"));

		List<StatusResponse> statusResponseList = ticketingSystem.getStatus();
		Assert.assertEquals(1, statusResponseList.size());
		Assert.assertEquals("KA-01-HH-2701", statusResponseList.get(0).getRegistrationNumber());
		Assert.assertEquals("Blue", statusResponseList.get(0).getColor());
		Assert.assertEquals(1, statusResponseList.get(0).getSlotNumber());

	}

	/**
	 * FakeParkingLot to emulate/mock ParkingLot class and override some functions
	 * for testing
	 *
	 */
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
