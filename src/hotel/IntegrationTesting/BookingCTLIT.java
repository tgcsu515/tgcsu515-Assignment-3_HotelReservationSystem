import hotel.credit.CreditCard;
import hotel.credit.CreditCardType;
import hotel.entities.Booking;
import hotel.entities.Guest;
import hotel.entities.Hotel;
import hotel.entities.Room;
import hotel.entities.RoomType;
import hotel.entities.ServiceType;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookingCTLIT {
    Hotel hotelObj;
    BookingCTL bookingCTLObj;
    BookingUI bookingUIObj;
    Booking bookingObj;
	
	@Before
    public void setUp() {
        hotelObj = new Hotel();
        bookingUIObj = new BookingUI(bookingCTLObj);
        Guest newGuest = new Guest("Peter Dawson", "Melbourne", 042345224);
        Room newRoom = new Room(1102, RoomType.SINGLE);
        Date arrivalDate = new Date();
        int stayLength = 2;
        int noOfOccupants = 1;
        CreditCard newCreditCard = new CreditCard(CreditCardType.VISA, 234688654, 248);
        bookingObj = new Booking(newGuest, newRoom, arrivalDate, stayLength, noOfOccupants, newCreditCard);
        bookingCTLObj = new BookingCTL(hotelObj);
        
    }
    
    @After
    public void tearDown() {
        bookingCTLObj = null;
        bookingUIObj = null;
        bookingObj = null;
    }
	
	@Test 
    public void testcreditDetailsEnteredRunTimeException() {
        String actualExceptionMessage = "";
        String expectedExceptionMessage = "BookingCTL: bookingTimesEntered : bad state : CREDIT";  
        try {
            bookingCTLObj.creditDetailsEntered(CreditCardType.VISA, 11223, 111); 
        } catch (Exception ex) {
            actualExceptionMessage = ex.getMessage();  
        }
        assertEquals(expectedExceptionMessage, actualExceptionMessage);  
    }
}