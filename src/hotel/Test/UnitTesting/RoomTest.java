package hotel.entities;
 import hotel.credit.CreditCard;
import hotel.credit.CreditCardType;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
 public class RoomTest {
    Booking booking;
    Room room;
    int id;
    RoomType roomType;
    Guest newGuestObj;
    Date arrivalDate = new Date();
    int stayLength;
    int noOfOccupants;
    CreditCard newCreditCardObj;
     @Before
    public void setUp() {
        newGuestObj = new Guest("John Wick", "Sydney", 042342222);
        room = new Room(1102, RoomType.SINGLE);
        //Date arrivalDate = new Date();
        stayLength = 4;
        noOfOccupants = 1;
        newCreditCardObj = new CreditCard(CreditCardType.VISA, 589635478, 545);
        booking = new Booking(newGuestObj, room, arrivalDate, stayLength, noOfOccupants, newCreditCardObj);
        room = new Room(1102, RoomType.SINGLE);
    }
	
	
    @After
    public void tearDown() {
        booking = null;
    }
	
    /*Test of checkIn method of Room class*/
    @Test
    public void testCheckIn() {
        String expectedResult = "OCCUPIED";
        room.checkIn(); 
        String actualResult = room.getState();
        assertEquals(expectedResult, actualResult);
    }
	 /*Test of RunTimeException in checkIn method of Room class*/
    @Test
    public void testCheckInRunTimeException() {
        String actualMessage = "";
        String expectedMessage = "Room: checkIn : bad state : OCCUPIED"; 
        try {
            room.setState();
            room.checkIn(); 
        } catch (Exception ex) {
            actualMessage = ex.getMessage(); 
        }
        assertEquals(expectedMessage, actualMessage);
    }
	 /*Test of checkOut method of Room class*/
    @Test
    public void testCheckOut() {
        String expectedResult = "OCCUPIED";
        room.checkIn();
        String actualResult = room.getState();
        assertEquals(expectedResult, actualResult);
    }
	 /*Test of RunTimeException in checkOut method of Room class*/
    @Test
    public void testCheckOutRunTimeException() {
        String actualExceptionMessage = "";
        String expectedExceptionMessage = "Room: checkOut : bad state : READY"; //Define expected result
        try {
            room.checkOut(booking); //Call the function
        } catch (Exception ex) {
            actualExceptionMessage = ex.getMessage(); //Get the actual result
        }
        assertEquals(expectedExceptionMessage, actualExceptionMessage); //Compare the  expected result with the actual result
    }
	 //Test of book method, of class Room.
    @Test
    public void testBook() {
        List<Booking> bookingList = null;
        Booking currentBookingObj = null;
        String actualResult = "";
         String expectedResult = "Room id is 1102";
        room.setState();
        room.book(newGuestObj, arrivalDate, stayLength, noOfOccupants, newCreditCardObj);
        bookingList = room.getBookingList(); //Get the booking list
        currentBookingObj = bookingList.get(0); //Get the first item from the list
        actualResult = "Room id is " + Integer.toString(currentBookingObj.getRoomId());
        assertEquals(expectedResult, actualResult); //Compare the  expected result with the actual result
    }
} 
