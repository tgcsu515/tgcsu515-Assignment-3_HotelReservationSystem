package hotel.checkout;

import hotel.credit.CreditAuthorizer;
import hotel.credit.CreditCardType;
import hotel.entities.Hotel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CheckoutCTLIT {
    CheckoutCTL checkOutCTL;
    CheckoutUI checkOutUI;
    Hotel hotel;
    
    @Before
    public void setUp() {
        //Create the integration testing backgroud
        hotel = new Hotel(); 
        checkOutCTL = new CheckoutCTL(hotel);
        checkOutUI = new CheckoutUI(checkOutCTL);
    }
    
    @After
    public void tearDown() {
        hotel = null; //Set hotel object to null
        checkOutCTL = null; //Set Checkout object to null
        checkOutUI = null; //Set CheckoutUI object to null
    }
    /**
     * Test of checkOutConfirmed method, of class CheckoutCTL.
     */
    @Test
    public void testCreditDetailsEntered() {
        String expectedcheckinCTLState = "CREDIT";
        checkOutCTL.setState();
        checkOutCTL.creditDetailsEntered(CreditCardType.VISA, 589635478, 909);
        String actualcheckinCTLState = checkOutCTL.getState();
        assertEquals(expectedcheckinCTLState, actualcheckinCTLState);
    }
    /*Test of RunTimeException in CheckOutConfirmed method of CheckOutCTL class*/
    @Test
    public void testCreditDetailsEnteredRunTimeException() {
        String actualExceptionMessage = "";
        String expectedExceptionMessage = "CheckoutCTL: bookingTimesEntered : bad state : null"; 
        try {
            checkOutCTL.creditDetailsEntered(CreditCardType.VISA, 589635478, 909);
        } catch (Exception ex) {
            actualExceptionMessage = ex.getMessage();
        }
        assertEquals(expectedExceptionMessage, actualExceptionMessage); 
    }
}
