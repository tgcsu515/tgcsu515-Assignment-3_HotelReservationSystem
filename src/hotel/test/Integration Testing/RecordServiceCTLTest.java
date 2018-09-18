package hotel.service;

import hotel.entities.Hotel;
import hotel.entities.ServiceType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RecordServiceCTLTest {
	
	RecordServiceCTL recordServiceCTLObj;
    RecordServiceUI recordServiceUIObj;
    Hotel hotelObj;

    @Before
    public void setUp() {
        hotelObj = new Hotel();
        recordServiceCTLObj = new RecordServiceCTL(hotelObj);
        recordServiceUIObj = new RecordServiceUI(recordServiceCTLObj);
    }

    @After
    public void tearDown() {
        hotelObj = null;
        recordServiceCTLObj = null;
        recordServiceUIObj = null;
    }



}
