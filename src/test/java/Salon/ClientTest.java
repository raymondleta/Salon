package Salon;

import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ClientTest {

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
    public void equals_returnsTrueIfClientsAretheSame() {
        Client firstClient = new Client("Tosh","1234","Kinyozi","1/11/1111", 1);
        Client secondClient = new Client("Tosh","1234","Kinyozi","1/11/1111", 1);
        assertTrue(firstClient.equals(secondClient));
    }
    @Test
    public void save_returnsTrueIfClientsAretheSame() {
        Client myClient = new Client("Tosh", "1234","Kinyozi","1/11/1111", 1);
        myClient.save();
        assertTrue(Client.all().get(0).equals(myClient));
    }
    @Test
    public void all_returnsAllInstancesOfTask_true() {
        Client firstClient = new Client("Tosh", "1234", "Salon", "12/12/1222", 1);
        firstClient.save();
        Client secondClient = new Client("Tooti","5678","Kinyozi","12/11/2010", 1);
        secondClient.save();
        assertEquals(true, Client.all().get(0).equals(firstClient));
        assertEquals(true, Client.all().get(1).equals(secondClient));
    }
    @Test
    public void save_assignsIdToObject() {
        Client myClient = new Client("Tooti","1234","Salon","12/12/2018", 1);
        myClient.save();
        Client savedClient = Client.all().get(0);
        assertEquals(myClient.getId(), savedClient.getId());
    }
    @Test
    public void getId_tasksInstantiateWithAnID() {
        Client myClient = new Client("Tosh","4567", "Kinyozi","", 1);
        myClient.save();
        assertTrue(myClient.getId() > 0);
    }
    @Test
    public void find_returnsClientWithSameId_secondClient() {
        Client firstClient = new Client("Tosh", "12345", "Salon","12/12/1200", 1);
        firstClient.save();
        Client secondClient = new Client("Tosh", "12345","Kinyozi","12/12/1200", 1);
        secondClient.save();
        assertEquals(Client.find(secondClient.getId()), secondClient);
    }
    @Test
    public void save_savesStylistIdIntoDB_true() {
        Stylist myStylist = new Stylist("Tosh","1234");
        myStylist.save();
        Client myClient = new Client("Tooti","1234","Kinyozi","12/11/2019" ,myStylist.getId());
        myClient.save();
        Client savedClient = Client.find(myClient.getId());
        assertEquals(savedClient.getStylistId(), myStylist.getId());
    }
    @Test
    public void update_updatesClientDescription_true() {
        Client myClient = new Client("Tosh", "5555","Salon","12/11/1111", 1);
        myClient.save();
        myClient.update("Tooti", "2222","Kinyozi","12/12/1222");
        assertEquals("Tooti", Client.find(myClient.getId()).getName());
    }
    @Test
    public void delete_deletesTask_true() {
        Client myClient = new Client("Tosh", "12345","Salon","12/12/2010", 1);
        myClient.save();
        int myClientId = myClient.getId();
        myClient.delete();
        assertEquals(null, Client.find(myClientId));
    }


}
