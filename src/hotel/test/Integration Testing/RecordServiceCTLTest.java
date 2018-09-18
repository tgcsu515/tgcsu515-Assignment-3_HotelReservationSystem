package hotel.service;

import hotel.entities.Hotel;
import hotel.entities.ServiceType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*
The following RecordServiceCTLTest class implements all the integration tests which were done to test the RecordServiceCTL.java.
Integration tests were done to the method which was implemented by Kasun Amarasinghe.
There are three test methods done in this Test file.
The SetUp() method creates the integration testing environment of RecordServiceCTL class.
*/

public class RecordServiceCTLTest {
	
	//Define all the require variables
	RecordServiceCTL recordServiceCTLObj;
    RecordServiceUI recordServiceUIObj;
    Hotel hotelObj;

    @Before
    public void setUp() {
		//Create the integration testing backgroud
        hotelObj = new Hotel(); 
        recordServiceCTLObj = new RecordServiceCTL(hotelObj);
        recordServiceUIObj = new RecordServiceUI(recordServiceCTLObj);
    }

    @After
    public void tearDown() {
        hotelObj = null; //Set hotel object to null
        recordServiceCTLObj = null; //Set RecordServiceCTL object to null
        recordServiceUIObj = null; //Set RecordServiceUI object to null
    }
	
	/*Test 0f Record Service State after calling the serviceDetailsEntered method of RecordServiceCTL class*/
    @Test
    public void testRecordServiceStateChangeOfServiceDetailsEntered() {
        String expectedRecordServiceState = "COMPLETED"; //Define expected result
        recordServiceCTLObj.setState();
        recordServiceCTLObj.serviceDetailsEntered(ServiceType.ROOM_SERVICE, 200);//Call the method
        String actualRecordServiceState = recordServiceCTLObj.getState(); //Get the current state of booking
        assertEquals(expectedRecordServiceState, actualRecordServiceState); //Compare the  expected result with the actual result
    }
	
	/*Test of Record Service UI State after calling the serviceDetailsEntered method of RecordServiceCTL class*/
    @Test
    public void testRecordServiceUIStateChangeOfServiceDetailsEntered() {
        String expectedRecordServiceUIstate = "COMPLETED"; //Define the expected result
        recordServiceCTLObj.setState();
        recordServiceCTLObj.serviceDetailsEntered(ServiceType.ROOM_SERVICE, 200);//Call the method
        String actualRecordServiceUIState = recordServiceCTLObj.getRecordServiceUIState(); //Get the current state of RecordServiceUI
        assertEquals(expectedRecordServiceUIstate, actualRecordServiceUIState); //Compare the  expected result with the actual result

    }
	
	/*Test of RunTimeException in serviceDetailsEntered method of RecordServiceCTL class*/
    @Test
    public void testServiceDetailsEnteredRunTimeException() {
        String actualExceptionMessage = "";
        String expectedExceptionMessage = "RecordServiceCTL: serviceDetailsEntered : bad state : ROOM"; //Define expected exception message
        try {
            recordServiceCTLObj.serviceDetailsEntered(ServiceType.ROOM_SERVICE, 200);//Call the method
        } catch (Exception ex) {
            actualExceptionMessage = ex.getMessage(); //Get the actual exception message
        }
        assertEquals(expectedExceptionMessage, actualExceptionMessage); //Compare the  expected result with the actual result
    }
}
