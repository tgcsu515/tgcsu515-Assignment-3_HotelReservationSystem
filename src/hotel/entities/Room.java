package hotel.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import hotel.credit.CreditCard;
import hotel.utils.IOUtils;

public class Room {
	private enum State {READY, OCCUPIED}	
	int id;
	RoomType roomType;
	List<Booking> bookings;
	State state;
	
	public Room(int id, RoomType roomType) {
		this.id = id;
		this.roomType = roomType;
		bookings = new ArrayList<>();
		state = State.READY;
	}
	public String toString() {
		return String.format("Room : %d, %s", id, roomType);
	}
	public int getId() {
		return id;
	}	
	public String getDescription() {
		return roomType.getDescription();
	}
	public RoomType getType() {
		return roomType;
	}
	// This method is implemented by Gurpreet Gill
	public List<Booking> getBookingList() {
		return Collections.unmodifiableList(bookings); //return the booking list
	}
	public boolean isAvailable(Date arrivalDate, int stayLength) {
		IOUtils.trace("Room: isAvailable");
		for (Booking b : bookings) {
			if (b.doTimesConflict(arrivalDate, stayLength)) {
				return false;
			}
		}
		return true;
	}	
	public boolean isReady() {
		return state == State.READY;
	}
	// This method is implemented by Gurpreet Gill
	public Booking book(Guest guest, Date arrivalDate, int stayLength, int numberOfOccupants, CreditCard creditCard) {
		Booking booking = new Booking(guest, this, arrivalDate, stayLength, numberOfOccupants, creditCard);
		bookings.add(booking);// insert data into dataset.
		return booking; // return the dataset.
	}
	// This method is implemented by Gurpreet Gill
	public void checkin() {
		// Check if room state is ready or not?
		if(state == State.READY){
			state = State.OCCUPIED; //set the room state occupied.
		}
		else{
			throw new RuntimeException("Room state is already occupied!");//show a runtime error message if the room state is already in occupied state.
		}
	}
	// This method is implemented by Gurpreet Gill
	public void checkout(Booking booking) {
		if (state != State.OCCUPIED) {// Check if room state is ready or not?
			String mesg = String.format("Room: checkout : bad state : %s", new Object[] { state });
			throw new RuntimeException(mesg);
		}
		bookings.remove(booking);//Empty the booking dataset.
		state = State.READY; //set the room state Ready.
		
	}
}
