package Salon;

import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {
    @Test
    public void Client_instantiatesCorrectly_true() {
        Client myClient = new Client("Raymond Gitonga", "0729000000", "Salon", "01/12/2019");
        assertEquals(true, myClient instanceof Client);
    }
    @Test
    public void Client_instantiatesWithName_String() {
        Client myClient = new Client("DM","1234","Salon", "01/12/2019");
        assertEquals("DM", myClient.getName());
    }
    @Test
    public void Client_instantiatesWithNumber_String() {
        Client myClient = new Client("DM","1234","Salon", "01/12/2019");
        assertEquals("1234", myClient.getPhoneNumber());
    }
    @Test
    public void Client_instantiatesWithService_String() {
        Client myClient = new Client("DM","1234","Salon", "01/12/2019");
        assertEquals("Salon", myClient.getService());
    }
    @Test
    public void Client_instantiatesWithDate_String() {
        Client myClient = new Client("DM","1234","Salon", "01/12/2019");
        assertEquals("01/12/2019", myClient.getDate());
    }


}
