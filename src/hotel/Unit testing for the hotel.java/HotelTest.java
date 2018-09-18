
// import the library file
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
        String expectedNumberForConfirmation = "18820182003"; 
        Guest newGuest = new Guest("John", "Melbourne", 041234577);
        Room newRoom = new Room(2003, RoomType.DOUBLE);
        Date arrivalDate = new Date();
        int stayLength = 3;
        int occupantNumber = 2;
        CreditCard newCreditCard = new CreditCard(CreditCardType.VISA, 234567788, 124);
        Booking bookingObj = new Booking(newGuest, newRoom, arrivalDate, stayLength, occupantNumber, newCreditCard);
        long currentConfirmationNumber = bookingObj.getConfirmationNumber();
        String actualNumberForConfirmation = Long.toString(currentConfirmationNumber);
        assertEquals(expectedNumberForConfirmation, actualNumberForConfirmation);
    }
	
	@Test
    public void testCheckinRunTimeException(){
        String actualResult = "";
        String expectedResult = "No booking found for confirmation number";
        try{
            hotelObj.checkin(0);
        }
        catch(Exception e){
            actualResult = e.getMessage();
        }
        assertEquals(actualResult, expectedResult);         
    }
	
	@Test
    public void testCheckoutRunTimeException(){
        String actualResult = "";
        String expectedResult = "no booking found  for room id";
        try{
            hotelObj.checkout(0);
        }
        catch(Exception ex){
            actualResult = ex.getMessage();
        }
        assertEquals(actualResult, expectedResult);
    }
}