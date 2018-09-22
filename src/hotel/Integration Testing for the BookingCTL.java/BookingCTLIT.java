
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
