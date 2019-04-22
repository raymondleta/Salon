package Salon;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Client {
    private String name;
    private String phoneNumber;
    private String service;
    private String date;
    private int id;


    public Client(String name, String phoneNumber, String service, String date) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.service = service;
        this.date = date;

    }

    public static List<Client> all() {
        String sql = "SELECT id, name, phoneNumber, service, date FROM clients";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Client.class);
        }
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getService() {
        return service;
    }
    public String getDate() {
        return date;
    }
    public int getId() {
        return id;
    }
    public static Client find(int id) {

    }

}
