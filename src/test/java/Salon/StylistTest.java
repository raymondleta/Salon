package Salon;
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class StylistTest {

    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "owner", "12345");
    }

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
    @Test
    public void all_returnsAllInstancesOfStylist_true() {
        Stylist firstStylist = new Stylist("ABC", "1234");
        Stylist secondStylist = new Stylist("DEF", "5678");
        assertEquals(true, Stylist.all().contains(firstStylist));
        assertEquals(true, Stylist.all().contains(secondStylist));
    }
    @Test
    public void clear_emptiesAllStylistsFromList_0() {
        Stylist testStylist = new Stylist("Tosh", "1234");
        Stylist.clear();
        assertEquals(Stylist.all().size(), 0);
    }
    @Test
    public void getId_stylistsInstantiateWithAnId_1() {
        Stylist.clear();
        Stylist testStylist = new Stylist("Tosh", "1234");
        assertEquals(1, testStylist.getId());
    }
    @Test
    public void find_returnsStylistWithSameId_secondStylist() {
        Stylist.clear();
        Stylist firstStylist = new Stylist("Tosh", "1234");
        Stylist secondStylist = new Stylist("Gitonga", "4567");
        assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
    }
    @Test
    public void getClients_initiallyReturnsEmptyList_ArrayList() {
        Stylist.clear();
        Stylist testCategory = new Stylist("Tosh","1234");
        assertEquals(0, testCategory.getClients().size());
    }
    @Test
    public void addClient_addsClientToList_true() {
        Stylist testStylist = new Stylist("John","1234");
        Client testClient = new Client("Amos","3456","Kinyozi","12/1/1234");
        testStylist.addClient(testClient);
        assertTrue(testStylist.getClients().contains(testClient));
    }
    @Test
    public void find_returnsNullWhenNoTaskFound_null() {
        assertTrue(Stylist.find(999) == null);
    }
}
