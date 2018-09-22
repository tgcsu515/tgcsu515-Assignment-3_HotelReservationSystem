/**Developed by Kanchan Bala,  Course : MIT, Subject: Professional Programming Practices, Team Name: TeamGenius
 * Student ID: 11635336
 * @version 9.0.4(build 9.0.4+ 11)
 * This test file has been created from "BookingCTL.java" class. and in this file Integration testing has been performed. For that, the required methods 
 * has been created,implemented and successfully tested. There are three methods which are tested.
**/

import hotel.credit.CreditCard;
import hotel.credit.CreditCardType;
import hotel.entities.Booking;
import hotel.entities.Guest;
import hotel.entities.Hotel;
import hotel.entities.Room;
import hotel.entities.RoomType;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookingCTLIT {
    Hotel hotelObj;
    BookingCTL bookingCTLObj;
    BookingUI bookingUIObj;
    Booking bookingObj;
	
	@Before
    public void setUp() {//created a setup for testing
        hotelObj = new Hotel();//created object of Hotel class
        bookingUIObj = new BookingUI(bookingCTLObj);//created object of BookingUI
        Guest newGuest = new Guest("Peter Dawson", "Melbourne", 042345224);//passing values to the parameters of guest
        Room newRoom = new Room(1102, RoomType.SINGLE);//passing values to the parameters of room
        Date arrivalDate = new Date();//creating object of date
        int stayLength = 2;
        int noOfOccupants = 1;
        CreditCard newCreditCard = new CreditCard(CreditCardType.VISA, 234688654, 248);//passing values to the parameters of creditcard
        bookingObj = new Booking(newGuest, newRoom, arrivalDate, stayLength, noOfOccupants, newCreditCard);//Create an instance of Booking class using the above parameters
        bookingCTLObj = new BookingCTL(hotelObj);//passing the hotelObj to the object of BookingCTL
        
    }
    
    @After
    public void tearDown() {
        bookingCTLObj = null;//initialize the objects with "null" value
        bookingUIObj = null;
        bookingObj = null;
    }
	
	@Test 
    public void testcreditDetailsEnteredRunTimeException() {
        String actualExceptionMessage = "";//defined actual result
        String expectedExceptionMessage = "BookingCTL: bookingTimesEntered : bad state : CREDIT";  //defined expected result
        try {
            bookingCTLObj.creditDetailsEntered(CreditCardType.VISA, 11223, 111);//calling creditDetailsEntered method and passing the values 
        } catch (Exception ex) {
            actualExceptionMessage = ex.getMessage();  //getting the actual exception message
        }
        assertEquals(expectedExceptionMessage, actualExceptionMessage); //comparing the actualExceptionMessage with expectedExceptionMessage 
    }
	
	@Test
    public void testBookingCTLState(){
        String actualResult = "";//defined actual result
        String expectedResult = "COMPLETED";  //defined expected result
        bookingCTLObj.setBookingCTLState();//calling setBookingCTLState() 
        bookingCTLObj.creditDetailsEntered(CreditCardType.VISA, 1111222233, 122);//calling creditDetailsEntered()
        actualResult = bookingCTLObj.getBookingCTLState();//getting the actual result by calling getBookingCTLState()
        assertEquals(expectedResult, actualResult);//comparing actual result with expected result
    }
	
	@Test
    public void testBookingUIState(){
        String actualResult = "";//defined actual result
        String expectedResult = "COMPLETED";//defined expected result
        bookingCTLObj.setBookingCTLState();//calling setBookingCTLState() 
        bookingCTLObj.creditDetailsEntered(CreditCardType.VISA, 1234567890, 133);//calling creditDetailsEntered()
        actualResult = bookingCTLObj.getCurrentBookingUIState();//getting the actual result by calling getBookingUIState()
        assertEquals(expectedResult, actualResult);//comparing actual result with expected result
    }
}