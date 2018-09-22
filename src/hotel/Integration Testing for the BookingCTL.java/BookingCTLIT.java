
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
	@Test
    public void testSBookingControlStateOfcreditDetailsEntered() {
        String actualResult = "";
        String expectedResult = "COMPLETED"; //Define expected result
        currentBookingControl.setBookingControlState();
        currentBookingControl.creditDetailsEntered(CreditCardType.VISA, 1236543982, 248);//Call the creditDetailsEntered() function
        actualResult = currentBookingControl.getBookingControlState(); //Get the current state of booking Control
        assertEquals(expectedResult, actualResult); //Compare the  expected result with the actual result
    }
