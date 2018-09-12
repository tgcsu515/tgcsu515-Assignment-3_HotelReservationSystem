package hotel.entities;

import hotel.credit.CreditCard;
import hotel.credit.CreditCardType;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookingTest {

    //Define all the require variables
    Booking bookingObj;
    Guest newGuest;
    Room newRoom;
    Date currentDate;
    int stayLength;
    int noOfOccupants;
    CreditCard newCreditCard;

    @Before
    public void setUp() {
        //Create the testing backgroud
        newGuest = new Guest("Peter Dawson", "Melbourne", 042345224);
        newRoom = new Room(1102, RoomType.SINGLE);
        Date arrivalDate = new Date();
        stayLength = 2;
        noOfOccupants = 1;
        newCreditCard = new CreditCard(CreditCardType.VISA, 234688654, 248);
        //Create an instance of Booking class using the above parameters
        bookingObj = new Booking(newGuest, newRoom, arrivalDate, stayLength, noOfOccupants, newCreditCard);
    }
	
	
    @After
    public void tearDown() {
        bookingObj = null;
    }
	
	/*Test of checkIn method of Booking class*/
    @Test
    public void testCheckIn() {
        String expectedBookingState = "CHECKED_IN"; //Define expected result
        bookingObj.checkIn(); //Call the method
        String actualBookingState = bookingObj.getState(); //Get the current state of booking
        assertEquals(expectedBookingState, actualBookingState); //Compare the  expected result with the actual result
    }
	
	/*Test of RunTimeException in checkIn method of Booking class*/
    @Test
    public void testCheckInRunTimeException() {
        String actualExceptionMessage = "";
        String expectedExceptionMessage = "Booking: checkIn : bad state : CHECKED_IN"; //Define expected result
        try {
            bookingObj.setState();
            bookingObj.checkIn(); //Call the method
        } catch (Exception ex) {
            actualExceptionMessage = ex.getMessage(); //Get the actual result
        }
        assertEquals(expectedExceptionMessage, actualExceptionMessage); //Compare the  expected result with the actual result
    }
	
	/*Test of addServiceCharge method of Booking class*/
    @Test
    public void testAddServiceCharge() {
        List<ServiceCharge> chargesList = null;
        ServiceCharge currentServiceChargeObj = null;
        String actualResult = "";

        String expectedResult = "Service Type is ROOM_SERVICE and Service Charge is 200.0"; //Define expected result
        bookingObj.setState();
        bookingObj.addServiceCharge(ServiceType.ROOM_SERVICE, 200); //Call the method
        chargesList = bookingObj.getCharges(); //Get the charges list
        currentServiceChargeObj = chargesList.get(0); //Get the first item from the list
        actualResult = "Service Type is " + currentServiceChargeObj.getType() + " and Service Charge is " + currentServiceChargeObj.getCost();
        assertEquals(expectedResult, actualResult); //Compare the  expected result with the actual result
    }
	
	
    /*Test of RunTimeException in addServiceCharge method of Booking class*/
    @Test
    public void testAddServiceChargeRunTimeException() {
        String actualExceptionMessage = "";
        String expectedExceptionMessage = "Booking: addServiceCharge : bad state : PENDING"; //Define expected result
        try {
            bookingObj.addServiceCharge(ServiceType.RESTAURANT, 100); //Call the method
        } catch (Exception ex) {
            actualExceptionMessage = ex.getMessage(); //Get actual result
        }
        assertEquals(expectedExceptionMessage, actualExceptionMessage); //Compare the  expected result with the actual result
    }
	
}