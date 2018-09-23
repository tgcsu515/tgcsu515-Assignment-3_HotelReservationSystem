/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.checkin;

import hotel.entities.Hotel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CheckinCTLIT {
    CheckinCTL checkinCTLObj;
    CheckinUI checkinUIObj;
    Hotel hotelObj;
    
    @Before
    public void setUp() {
        //Create the integration testing backgroud
        hotelObj = new Hotel();
        checkinCTLObj = new CheckinCTL(hotelObj);
        checkinUIObj = new CheckinUI(checkinCTLObj);
    }
    
    @After
    public void tearDown() {
        hotelObj = null; //Set hotel object to null
        checkinCTLObj = null; //Set CheckinObj object to null
        checkinUIObj = null; //Set CheckinUIObj object to null
    }
    /**
     * Test of checkInConfirmed method, of class CheckinCTL.
     */
    @Test
    public void testCheckInConfirmed() {
        String expectedcheckinCTLState = "COMPLETED"; //Define expected result
        checkinCTLObj.setState();
        checkinCTLObj.checkInConfirmed(true);//Call the method
        String actualcheckinCTLState = checkinCTLObj.getState(); //Get the current state of booking
        assertEquals(expectedcheckinCTLState, actualcheckinCTLState); //Compare the  expected result with the actual result
    }
}
