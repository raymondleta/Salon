package Salon;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {

    @Test
    public void category_instantiatesCorrectly_true() {
        Stylist testStylist = new Stylist("Tosh");
        assertEquals(true, testStylist instanceof Stylist);
    }
}
