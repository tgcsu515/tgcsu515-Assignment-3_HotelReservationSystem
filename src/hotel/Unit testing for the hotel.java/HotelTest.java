
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

// created the HotelTest class
public class HotelTest {
    Hotel hotelObj; // created the hotelObj variable
	
	@Before
    public void setUp() {
        hotelObj = new Hotel(); // created the object for the Hotel class
    }
    
    @After
    public void tearDown() {
        hotelObj = null; // put the hotelObj value to null
    }
	
	// created the testBook() method to test the Book() method 
	@Test
    public void testBook(){
        String expectedNumberForConfirmation = "18820182003"; //initialize the expected confirmation number
        Guest newGuest = new Guest("John", "Melbourne", 041234577); // created the  object for Guest class and pass the values for the Guest parameter
        Room newRoom = new Room(2003, RoomType.DOUBLE); // created the object for Room class and pass the values for the Guest parameter
        Date arrivalDate = new Date(); //created the object for the  Date
        int stayLength = 3; // initialize and define the stayLength variable  
        int occupantNumber = 2; // initialize and define the occupantNumber variable
        CreditCard newCreditCard = new CreditCard(CreditCardType.VISA, 234567788, 124);// create the object for the CreditCard class and pass the parameter values
        Booking bookingObj = new Booking(newGuest, newRoom, arrivalDate, stayLength, occupantNumber, newCreditCard); // created the bookingObj for the booking class
        long currentConfirmationNumber = bookingObj.getConfirmationNumber(); // get the confirmation number
        String actualNumberForConfirmation = Long.toString(currentConfirmationNumber); // get the actual confirmation number
        assertEquals(expectedNumberForConfirmation, actualNumberForConfirmation); // compare the results
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
    public void testAddServiceChargeRunTimeException() {
        String actualResult = "";
        String expectedResult = "no booking found  for specified room id"; 
        try {
            hotelObj.addServiceCharge(0, ServiceType.BAR_FRIDGE, 200); 
        }
        catch (Exception e) {
            actualResult = e.getMessage(); 
        }
        assertEquals( actualResult, expectedResult); 
    
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