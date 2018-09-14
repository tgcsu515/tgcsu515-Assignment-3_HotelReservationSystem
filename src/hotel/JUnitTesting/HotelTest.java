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
}