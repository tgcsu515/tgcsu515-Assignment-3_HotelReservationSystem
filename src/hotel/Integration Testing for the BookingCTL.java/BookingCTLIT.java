/* Student Name: Amandeep Kaur
  Student Id: 11645658
  Lecturer Name : Recep ULUSOY
  This BookingCTLIT.java class test the three methods and show the pass result 100% */
  

import hotel.credit.CreditCardType;
import hotel.entities.Hotel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookingCTLIT {

    BookingCTL currentBookingControl;
    Hotel currentHotel;
	
	@Before
    public void setUp() {
        currentHotel = new Hotel();
        currentBookingControl = new BookingCTL(currentHotel);
    }

    @After
    public void tearDown() {
        currentBookingControl = null;
        currentHotel = null;
    }
	
	/*test of Booking Control State in creditDetailsEntered method of class
     BookingCTL */
	@Test
    public void testSBookingControlStateOfcreditDetailsEntered() {
        String actualResult = "";
        String expectedResult = "COMPLETED"; //Define expected result
        currentBookingControl.setBookingControlState();
        currentBookingControl.creditDetailsEntered(CreditCardType.VISA, 1236543982, 248);//Call the creditDetailsEntered() function
        actualResult = currentBookingControl.getBookingControlState(); //Get the current state of booking Control
        assertEquals(expectedResult, actualResult); //Compare the  expected result with the actual result
    }
	
	/*test of Booking UI State in creditDetailsEntered method of class
     BookingCTL */
	@Test
    public void testSBookingUIStateOfcreditDetailsEntered() {
        String actualResult = ""; //Define actual result variable
        String expectedResult = "COMPLETED"; //Define expected result variable
        currentBookingControl.setBookingControlState();
        currentBookingControl.creditDetailsEntered(CreditCardType.MASTERCARD, 1236874922, 562);//Call the creditDetailsEntered() function
        actualResult = currentBookingControl.getBookingUIState(); //Get the current state of booking UI
        assertEquals(expectedResult, actualResult); //Compare the  expected result with the actual result
    }
	/* test to check the RunTimeEXception of class BookingCTL*/
	
	@Test
    
    public void testcreditDetailsEnteredRunTimeException() {
        String actualExceptionMessage = ""; // define the actual exception result
        String expectedExceptionMessage = "BookingCTL: bookingTimesEntered : bad state";   // define the excepted exception variable
        try {
            currentBookingControl.creditDetailsEntered(CreditCardType.VISA, 11223, 111); 
        } catch (Exception ex) { 
            actualExceptionMessage = ex.getMessage(); //throw the exception
        }
        assertEquals(expectedExceptionMessage, actualExceptionMessage); //Compare the  expectedExceptionMessage with the actualExceptionMessage
    }
    
}
