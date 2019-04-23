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

//    @Test
//    public void Client_instantiatesCorrectly_true() {
//        Client myClient = new Client("Raymond Gitonga", "0729000000", "Salon", "01/12/2019");
//        assertEquals(true, myClient instanceof Client);
//    }
//    @Test
//    public void Client_instantiatesWithName_String() {
//        Client myClient = new Client("DM","1234","Salon", "01/12/2019");
//        assertEquals("DM", myClient.getName());
//    }
//    @Test
//    public void Client_instantiatesWithNumber_String() {
//        Client myClient = new Client("DM","1234","Salon", "01/12/2019");
//        assertEquals("1234", myClient.getPhoneNumber());
//    }
//    @Test
//    public void Client_instantiatesWithService_String() {
//        Client myClient = new Client("DM","1234","Salon", "01/12/2019");
//        assertEquals("Salon", myClient.getService());
//    }
//    @Test
//    public void Client_instantiatesWithDate_String() {
//        Client myClient = new Client("DM","1234","Salon", "01/12/2019");
//        assertEquals("01/12/2019", myClient.getDate());
//    }
//    @Test
//    public void all_returnsAllInstancesOfClient_true() {
//        Client firstClient = new Client("Tosh","4567","Kinyozi", "1/1/1111");
//        Client secondClient = new Client("Gitonga", "4568","Salon","12/1/1990");
//        assertEquals(true, Client.all().contains(firstClient));
//        assertEquals(true, Client.all().contains(secondClient));
//    }
//
//    @Test
//    public void getId_clientsInstantiateWithAnID_1() {
//        Client myClient = new Client("Tosh","4567","Kinyozi", "1/1/1111");
//        assertEquals(1, myClient.getId());
//    }
//    @Test
//    public void find_returnsClientWithSameId_secondTask() {
//
//        Client firstCLient = new Client("Tosh","4567","Kinyozi", "1/1/1111");
//        Client secondClient = new Client("MP","1234","Salon", "12/12/2010");
//        assertEquals(Client.find(secondClient.getId()), secondClient);
//    }


}
