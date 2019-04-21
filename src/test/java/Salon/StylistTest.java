package Salon;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {

    @Test
    public void category_instantiatesCorrectly_true() {
        Stylist testStylist = new Stylist("Tosh","1234");
        assertEquals(true, testStylist instanceof Stylist);
    }
    @Test
    public void getName_stylistInstantiatesWithName_String() {
        Stylist testStylist = new Stylist("Tosh","1234");
        assertEquals("Tosh", testStylist.getName());
    }

    @Test
    public void getphoneNumber_stylistInstantiatesWithphoneNumber_String() {
        Stylist testStylist = new Stylist("Tosh","12345");
        assertEquals("12345", testStylist.getPhoneNumber());
    }
}
