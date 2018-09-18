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
	
	/*Test Record Service State after calling the serviceDetailsEntered method of RecordServiceCTL class*/
    @Test
    public void testRecordServiceStateChangeOfServiceDetailsEntered() {
        String expectedRecordServiceState = "COMPLETED"; //Define expected result
        recordServiceCTLObj.setState();
        recordServiceCTLObj.serviceDetailsEntered(ServiceType.ROOM_SERVICE, 200);//Call the method
        String actualRecordServiceState = recordServiceCTLObj.getState(); //Get the current state of booking
        assertEquals(expectedRecordServiceState, actualRecordServiceState); //Compare the  expected result with the actual result
    }
	
	/*Test Record Service UI State after calling the serviceDetailsEntered method of RecordServiceCTL class*/
    @Test
    public void testRecordServiceUIStateChangeOfServiceDetailsEntered() {
        String expectedRecordServiceUIstate = "COMPLETED"; //Define the expected result
        recordServiceCTLObj.setState();
        recordServiceCTLObj.serviceDetailsEntered(ServiceType.ROOM_SERVICE, 200);//Call the method
        String actualRecordServiceUIState = recordServiceCTLObj.getRecordServiceUIState(); //Get the current state of RecordServiceUI
        assertEquals(expectedRecordServiceUIstate, actualRecordServiceUIState); //Compare the  expected result with the actual result

    }



}
