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
	
    /*Test of checkIn method of Booking class*/
    @Test
    public void testCheckIn() {
        String expectedResult = "OCCUPIED";
        room.checkIn(); 
        String actualResult = room.getState();
        assertEquals(expectedResult, actualResult);
    }
	 /*Test of RunTimeException in checkIn method of Booking class*/
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
} 
