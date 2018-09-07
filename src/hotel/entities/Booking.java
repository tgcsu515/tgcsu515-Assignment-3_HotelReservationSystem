package hotel.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import hotel.credit.CreditCard;
import hotel.utils.IOUtils;

public class Booking {
	
	private enum State {PENDING, CHECKED_IN, CHECKED_OUT};
	
	private Guest guest;
	private Room room;
	private Date bookedArrival; 
	private int stayLength;
	int numberOfOccupants;
	long confirmationNumber;
	CreditCard creditCard;
	
	private List<ServiceCharge> charges;
	
	private State state;


	
	public Booking(Guest guest, Room room, 
			Date arrivalDate, int stayLength, 
			int numberOfOccupants, 
			CreditCard creditCard) {
		
		this.guest = guest;
		this.room = room;
		this.bookedArrival = arrivalDate;
		this.stayLength = stayLength;
		this.numberOfOccupants = numberOfOccupants;
		this.confirmationNumber = generateConfirmationNumber(room.getId(), arrivalDate);
		this.creditCard = creditCard;
		this.charges = new ArrayList<>();
		this.state = State.PENDING;
	}

	
	private long generateConfirmationNumber(int roomId, Date arrivalDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(arrivalDate);
		
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		String numberString = String.format("%d%d%d%d", day, month, year, roomId);
		
		return Long.parseLong(numberString);
	}


	public boolean doTimesConflict(Date requestedArrival, int stayLength) {
		IOUtils.trace("Booking: timesConflict");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(bookedArrival);
		calendar.add(Calendar.DATE, stayLength);
		Date bookedDeparture = calendar.getTime();
		
		calendar.setTime(requestedArrival);
		calendar.add(Calendar.DATE, stayLength);
		Date requestedDeparture = calendar.getTime();
		
		boolean doesConflict = requestedArrival.before(bookedDeparture) && 
				requestedDeparture.after(bookedArrival);

		return doesConflict;
	}


	public long getConfirmationNumber() {
		return confirmationNumber;
	}


	public int getRoomId() {
		return room.getId();
	}
	
	
	public Room getRoom() {
		return room;
	}


	public Date getArrivalDate() {
		return bookedArrival;
	}


	public int getStayLength() {
		return stayLength;
	}


	public Guest getGuest() {
		return guest;
	}


	public CreditCard getCreditCard() {
		return creditCard;
	}


	public boolean isPending() {
		return state == State.PENDING;
	}


	public boolean isCheckedIn() {
		return state == State.CHECKED_IN;
	}


	public boolean isCheckedOut() {
		return state == State.CHECKED_OUT;
	}


	public List<ServiceCharge> getCharges() {
		return Collections.unmodifiableList(charges);
	}

	/*The functionality of checkIn() function was implemented by Kasun Amarasinghe.
	  The functionality was implemented according to the given guidelines.*/
	public void checkIn() {
        //Check if the Booking State is pending or not
        if (isPending()) {
            this.room.checkin(); //Set the Room associated with booking state as OCCUPIED
            this.state = State.CHECKED_IN; //Set the Booking State as CHECKED_IN
        } else {
            throw new RuntimeException("Booking State is not pending."); //Throw a RunTimeException if the Booking State is not pending
        }
    }

	/*The functionality of addServiceCharge() function was implemented by Kasun Amarasinghe.
	  The functionality was implemented according to the given guidelines.*/
	public void addServiceCharge(ServiceType serviceType, double cost) {
        //Check if the Booking State is checked in or not
        if (isCheckedIn()) {
            ServiceCharge latestServiceCharge = new ServiceCharge(serviceType, cost); //Create a new ServiceCharge
            this.charges.add(latestServiceCharge); //Add the created ServiceCharge to the charges list
        } else {
            throw new RuntimeException("Booking State is not Checked In."); //Throw a RunTimeException if the Booking State is not checked in
        }
    }


	public void checkOut() {
		// TODO Auto-generated method stub
		if (isCheckedIn()) {
            this.room.checkout(this);
            this.state = State.CHECKED_OUT;
        } else {
            throw new RuntimeException("Booking State is not Checked In.");
        }
	}

}
