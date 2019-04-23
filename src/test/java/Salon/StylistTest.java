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
//    @Test
//    public void clear_emptiesAllStylistsFromList_0() {
//        Stylist testStylist = new Stylist("Tosh", "1234");
//        Stylist.clear();
//        assertEquals(Stylist.all().size(), 0);
//    }
//    @Test
//    public void getId_stylistsInstantiateWithAnId_1() {
//        Stylist.clear();
//        Stylist testStylist = new Stylist("Tosh", "1234");
//        assertEquals(1, testStylist.getId());
//    }
//    @Test
//    public void find_returnsStylistWithSameId_secondStylist() {
//        Stylist.clear();
//        Stylist firstStylist = new Stylist("Tosh", "1234");
//        Stylist secondStylist = new Stylist("Gitonga", "4567");
//        assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
//    }
//    @Test
//    public void getClients_initiallyReturnsEmptyList_ArrayList() {
//        Stylist.clear();
//        Stylist testCategory = new Stylist("Tosh","1234");
//        assertEquals(0, testCategory.getClients().size());
//    }
//    @Test
//    public void addClient_addsClientToList_true() {
//        Stylist testStylist = new Stylist("John","1234");
//        Client testClient = new Client("Amos","3456","Kinyozi","12/1/1234");
//        testStylist.addClient(testClient);
//        assertTrue(testStylist.getClients().contains(testClient));
//    }
//    @Test
//    public void find_returnsNullWhenNoTaskFound_null() {
//        assertTrue(Stylist.find(999) == null);
//    }
}
