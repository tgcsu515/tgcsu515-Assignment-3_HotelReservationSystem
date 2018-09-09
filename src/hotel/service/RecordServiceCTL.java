package hotel.service;

import hotel.entities.Booking;
import hotel.entities.Hotel;
import hotel.entities.ServiceType;
import hotel.utils.IOUtils;

public class RecordServiceCTL {
	
	private static enum State {ROOM, SERVICE, CHARGE, CANCELLED, COMPLETED};
	
	private Hotel hotel;
	private RecordServiceUI recordServiceUI;
	private State state;
	
	private Booking booking;
	private int roomNumber;


	public RecordServiceCTL(Hotel hotel) {
		this.recordServiceUI = new RecordServiceUI(this);
		state = State.ROOM;
		this.hotel = hotel;
	}

	
	public void run() {		
		IOUtils.trace("PayForServiceCTL: run");
		recordServiceUI.run();
	}


	public void roomNumberEntered(int roomNumber) {
		if (state != State.ROOM) {
			String mesg = String.format("PayForServiceCTL: roomNumberEntered : bad state : %s", state);
			throw new RuntimeException(mesg);
		}
		booking = hotel.findActiveBookingByRoomId(roomNumber);
		if (booking == null) {
			String mesg = String.format("No active booking for room id: %d", roomNumber);
			recordServiceUI.displayMessage(mesg);
		}
		else {
			this.roomNumber = roomNumber;
			state = State.SERVICE;
			recordServiceUI.setState(RecordServiceUI.State.SERVICE);
		}
	}
	
	
	public void serviceDetailsEntered(ServiceType serviceType, double cost) {
		//Throw a RunTimeException if the state is not equal to SERVICE
        if (state != State.SERVICE) {
            String mesg = String.format("RecordServiceCTL: serviceDetailsEntered : bad state : %s", state); //Create the exception message
            throw new RuntimeException(mesg);
        }
        hotel.addServiceCharge(roomNumber, serviceType, cost); //Call addServiceCharge() method of Hotel class
        //Call displayServiceChargeMessage() method of RecordServiceUI class
        recordServiceUI.displayServiceChargeMessage(roomNumber, cost, "This is the Service Description");
        state = State.COMPLETED; //Set the State to COMPLETED
        recordServiceUI.setState(RecordServiceUI.State.COMPLETED); //Set the RecordServiceUI State to COMPLETED   
	}


	public void cancel() {
		recordServiceUI.displayMessage("Pay for service cancelled");
		state = State.CANCELLED;
		recordServiceUI.setState(RecordServiceUI.State.CANCELLED);
	}


	public void completed() {
		recordServiceUI.displayMessage("Pay for service completed");
	}


	

}
