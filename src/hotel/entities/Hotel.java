package hotel.entities;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import hotel.credit.CreditCard;
import hotel.utils.IOUtils;

public class Hotel {
	
	private Map<Integer, Guest> guests;
	public Map<RoomType, Map<Integer,Room>> roomsByType;
	public Map<Long, Booking> bookingsByConfirmationNumber;
	public Map<Integer, Booking> activeBookingsByRoomId;
	
	
	public Hotel() {
		guests = new HashMap<>();
		roomsByType = new HashMap<>();
		for (RoomType rt : RoomType.values()) {
			Map<Integer, Room> rooms = new HashMap<>();
			roomsByType.put(rt, rooms);
		}
		bookingsByConfirmationNumber = new HashMap<>();
		activeBookingsByRoomId = new HashMap<>();
	}

	
	public void addRoom(RoomType roomType, int id) {
		IOUtils.trace("Hotel: addRoom");
		for (Map<Integer, Room> rooms : roomsByType.values()) {
			if (rooms.containsKey(id)) {
				throw new RuntimeException("Hotel: addRoom : room number already exists");
			}
		}
		Map<Integer, Room> rooms = roomsByType.get(roomType);
		Room room = new Room(id, roomType);
		rooms.put(id, room);
	}

	
	public boolean isRegistered(int phoneNumber) {
		return guests.containsKey(phoneNumber);
	}

	
	public Guest registerGuest(String name, String address, int phoneNumber) {
		if (guests.containsKey(phoneNumber)) {
			throw new RuntimeException("Phone number already registered");
		}
		Guest guest = new Guest(name, address, phoneNumber);
		guests.put(phoneNumber, guest);		
		return guest;
	}

	
	public Guest findGuestByPhoneNumber(int phoneNumber) {
		Guest guest = guests.get(phoneNumber);
		return guest;
	}

	
	public Booking findActiveBookingByRoomId(int roomId) {
		Booking booking = activeBookingsByRoomId.get(roomId);;
		return booking;
	}


	public Room findAvailableRoom(RoomType selectedRoomType, Date arrivalDate, int stayLength) {
		IOUtils.trace("Hotel: checkRoomAvailability");
		Map<Integer, Room> rooms = roomsByType.get(selectedRoomType);
		for (Room room : rooms.values()) {
			IOUtils.trace(String.format("Hotel: checking room: %d",room.getId()));
			if (room.isAvailable(arrivalDate, stayLength)) {
				return room;
			}			
		}
		return null;
	}

	
	public Booking findBookingByConfirmationNumber(long confirmationNumber) {
		return bookingsByConfirmationNumber.get(confirmationNumber);
	}

	
	public long book(Room room, Guest guest, Date arrivalDate, int stayLength, int occupantNumber, CreditCard creditCard) {
		Booking roomBooking = room.book(guest, arrivalDate, stayLength, occupantNumber, creditCard);	//pass the arguments to get the additional details for booking a room in hotel
		long confirmationNumber = roomBooking.getConfirmationNumber();		//calling getConfirmationNumber method
		bookingsByConfirmationNumber.put(Long.valueOf(confirmationNumber), roomBooking);	//pass the value of confirmationNumber
		return confirmationNumber;		
	}

	
	public void checkin(long confirmationNumber) {
		Booking currentBooking = (Booking)bookingsByConfirmationNumber.get(Long.valueOf(confirmationNumber));//getting the confirmation number of booking
		if (currentBooking == null){	//if block to check if the current booking is null
			String exceptionMessage = String.format("Hotel: checkin: No booking found for confirmation number %d", new Object[]{ Long.valueOf(confirmationNumber)});
			throw new RunTimeException(exceptionMessage); //exception if the currentBooking is null
		}
		int roomId = currentBooking.getRoomId();
		currentBooking.checkIn();
		activeBookingsByRoomId.put(Integer.valueOf(roomId), currentBooking);
	}


	public void addServiceCharge(int roomId, ServiceType serviceType, double cost) {
		Booking booking = (Booking)activeBookingsByRoomId.get(Integer.valueOf(roomId));//getting roomId
		if(booking == null){//if condition to check whether the room ID is null
			String errorMessage = String.format("Hotel: checkout: no booking present for room id : %d", new Object[] { Integer.valueOf(roomId) });
			throw new RuntimeException(errorMessage);//if null then throw runtime exception
		}
		booking.checkOut();//else proceed to checkout
		activeBookingsByRoomId.remove(Integer.valueOf(roomId));
	}

	
	public void checkout(int roomId) {
		// TODO Auto-generated method stub
	}


}
