package Salon;

import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {
    @Test
    public void Client_instantiatesCorrectly_true() {
        Client myClient = new Client("Mow the lawn");
        assertEquals(true, myClient instanceof Client);
    }
}
