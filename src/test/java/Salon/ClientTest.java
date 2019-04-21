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
    @Test
    public void all_returnsAllInstancesOfClient_true() {
        Client firstClient = new Client("Tosh","4567","Kinyozi", "1/1/1111");
        Client secondClient = new Client("Gitonga", "4568","Salon","12/1/1990");
        assertEquals(true, Client.all().contains(firstClient));
        assertEquals(true, Client.all().contains(secondClient));
    }
    @Test
    public void clear_emptiesAllClientsFromArrayList_0() {
        Client myClient = new Client("MP","1234","Salon", "12/12/2010");
        Client.clear();
        assertEquals(Client.all().size(), 0);
    }

    @Test
    public void getId_clientsInstantiateWithAnID_1() {
        Client.clear();
        Client myClient = new Client("Tosh","4567","Kinyozi", "1/1/1111");
        assertEquals(1, myClient.getId());
    }
    @Test
    public void find_returnsClientWithSameId_secondTask() {
        Client.clear();
        Client firstCLient = new Client("Tosh","4567","Kinyozi", "1/1/1111");
        Client secondClient = new Client("MP","1234","Salon", "12/12/2010");
        assertEquals(Client.find(secondClient.getId()), secondClient);
    }


}
