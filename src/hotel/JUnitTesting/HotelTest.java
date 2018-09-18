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
    Hotel hotelObj;	//created required variable
   
    
    @Before
    public void setUp() {
        hotelObj = new Hotel();	//created object of Hotel class
    }
    
    @After
    public void tearDown() {
        hotelObj = null;		//initialize the object with "null" value
    }
	
	//Test of "book" method of Hotel class
	@Test
    public void testBook(){
        String expectedConfirmationNumber = "14820182001"; //defined expected result
        Guest newGuest = new Guest("David", "Melbourne", 041234566); //passing values to the parameters of guest
        Room newRoom = new Room(2001, RoomType.DOUBLE);//passing values to the parameters of room
        Date arrivalDate = new Date();//creating object of date
        int stayLength = 3;//defining and initialize staylength
        int noOfOccupants = 2;//defining and initialize noOfOccupants
        CreditCard newCreditCard = new CreditCard(CreditCardType.VISA, 234567788, 123);//passing values to the parameters of creditcard
        Booking bookingObj = new Booking(newGuest, newRoom, arrivalDate, stayLength, noOfOccupants, newCreditCard);//Create an instance of Booking class using the above parameters
        long currentConfirmationNumber = bookingObj.getConfirmationNumber();//calling getConfirmationNumber method
        String actualConfirmationNumber = Long.toString(currentConfirmationNumber);
        assertEquals(expectedConfirmationNumber, actualConfirmationNumber);//comparing actual result with expected
    }
	
	//Test of "checkin" method of Hotel class
	@Test
    public void testCheckinRunTimeException(){
        String actualExceptionMessage = "";
        String expectedExceptionMessage = "Current Booking is null";//defined expected result
        try{
            hotelObj.checkin(0);//calling checkin method of hotel class
        }
        catch(Exception ex){
            actualExceptionMessage = ex.getMessage();//getting actual result
        }
        assertEquals(expectedExceptionMessage, actualExceptionMessage);  //comparing actual result with expected       
    }
	
	//Test of "checkout" method of Hotel class
	@Test
    public void testCheckoutRunTimeException(){
        String actualExceptionMessage = "";
        String expectedExceptionMessage = "Hotel: checkout: no booking present for room id";//defined expected result
        try{
            hotelObj.checkout(0);//calling checkout method of hotel class
        }
        catch(Exception e){
            actualExceptionMessage = e.getMessage();//getting actual result
        }
        assertEquals(expectedExceptionMessage, actualExceptionMessage);//comparing actual result with expected
    }
	
	//Test of "addServiceCharge" method of Hotel class
	@Test
    public void testAddServiceChargeRunTimeException() {
        String actualExceptionMessage = "";
        String expectedExceptionMessage = "no booking present for room id"; //Define expected result
        try {
            hotelObj.addServiceCharge(0, ServiceType.BAR_FRIDGE, 100); //Calling addServiceCharge method of hotel class
        } catch (Exception ex) {
            actualExceptionMessage = ex.getMessage(); //Get actual result
        }
        assertEquals(expectedExceptionMessage, actualExceptionMessage); //Compare the  expected result with the actual result
    }
}