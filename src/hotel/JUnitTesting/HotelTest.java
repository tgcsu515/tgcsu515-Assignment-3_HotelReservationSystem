import hotel.credit.CreditCard;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import hotel.credit.CreditCardType;

public class HotelTest {
    Hotel hotelObj;
   
    
    @Before
    public void setUp() {
        hotelObj = new Hotel();
    }
    
    @After
    public void tearDown() {
        hotelObj = null;
    }
	
	@Test
    public void testBook(){
        String expectedConfirmationNumber = "14820182001"; 
        Guest newGuest = new Guest("David", "Melbourne", 041234566);
        Room newRoom = new Room(2001, RoomType.DOUBLE);
        Date arrivalDate = new Date();
        int stayLength = 3;
        int noOfOccupants = 2;
        CreditCard newCreditCard = new CreditCard(CreditCardType.VISA, 234567788, 123);
        Booking bookingObj = new Booking(newGuest, newRoom, arrivalDate, stayLength, noOfOccupants, newCreditCard);
        long currentConfirmationNumber = bookingObj.getConfirmationNumber();
        String actualConfirmationNumber = Long.toString(currentConfirmationNumber);
        assertEquals(expectedConfirmationNumber, actualConfirmationNumber);
    }
	
	@Test
    public void testCheckinRunTimeException(){
        String actualExceptionMessage = "";
        String expectedExceptionMessage = "Current Booking is null";
        try{
            hotelObj.checkin(0);
        }
        catch(Exception ex){
            actualExceptionMessage = ex.getMessage();
        }
        assertEquals(expectedExceptionMessage, actualExceptionMessage);         
    }
	
	@Test
    public void testCheckoutRunTimeException(){
        String actualExceptionMessage = "";
        String expectedExceptionMessage = "Hotel: checkout: no booking present for room id";
        try{
            hotelObj.checkout(0);
        }
        catch(Exception e){
            actualExceptionMessage = e.getMessage();
        }
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }
}