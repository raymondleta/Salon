package Salon;
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class StylistTest {

    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "owner", "12345");
    }

    @After
    public void tearDown() {
        try(Connection con = DB.sql2o.open()) {
            String deleteClientsQuery = "DELETE FROM clients *;";
            String deleteStylistsQuery = "DELETE FROM stylists *;";
            con.createQuery(deleteClientsQuery).executeUpdate();
            con.createQuery(deleteStylistsQuery).executeUpdate();
        }
    }
    @Test
    public void all_returnsAllInstancesOfCategory_true() {
        Stylist firstStylist = new Stylist("Jogn","3456");
        firstStylist.save();
        Stylist secondStylist = new Stylist("Petessake", "6789");
        secondStylist.save();
        assertEquals(true, Stylist.all().get(0).equals(firstStylist));
        assertEquals(true, Stylist.all().get(1).equals(secondStylist));
    }
    @Test
    public void save_assignsIdToObject() {
        Stylist myStylist = new Stylist("John","4444");
        myStylist.save();
        Stylist savedStylist = Stylist.all().get(0);
        assertEquals(myStylist.getId(), savedStylist.getId());
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
    public void getId_stylistsInstantiateWithAnId_1() {
        Stylist testStylist = new Stylist("Jane","456");
        testStylist.save();
        assertTrue(testStylist.getId() > 0);
    }
    @Test
    public void find_returnsStylistWithSameId_secondStylist() {
        Stylist firstStylist = new Stylist("John","12345");
        firstStylist.save();
        Stylist secondStylist = new Stylist("Peter","1234");
        secondStylist.save();
        assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
    }
    @Test
    public void getClients_retrievesALlClientsFromDatabase_clientsList() {
        Stylist myStylist = new Stylist("Noni","1234");
        myStylist.save();
        Client firstClient = new Client("John","3333","Kinyozi",
                "12/12/2012", myStylist.getId());
        firstClient.save();
        Client secondClient = new Client("DJane","1234","Salon","12/12/2010", myStylist.getId());
        secondClient.save();
        Client[] tasks = new Client[] { firstClient, secondClient };
        assertTrue(myStylist.getClients().containsAll(Arrays.asList(tasks)));
    }
    @Test
    public void update_updatesStylistDetails_true() {
        Stylist myStylist = new Stylist("John", "12345");
        myStylist.save();
        myStylist.update("Peter","12345");
        assertEquals("Peter", Stylist.find(myStylist.getId()).getName());
    }
}
