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
    @Override
    public boolean equals(Object otherClient) {
        if (!(otherClient instanceof Client)) {
            return false;
        } else{
            Client newClient = (Client) otherClient;
            return (this.getName().equals(newClient.getName()) &&
                    this.getPhoneNumber().equals(newClient.getPhoneNumber()) &&
                    this.getService().equals(newClient.getService()) &&
                    this.getDate().equals(newClient.getDate()) && this.getId() == newClient.getId());

        }
    }
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO tasks (name, phoneNumber, service, date) VALUES (:name, :phoneNumber, :service, :date)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("phoneNumber", this.phoneNumber)
                    .addParameter("service", this.service)
                    .addParameter("date", this.date)
                    .executeUpdate()
                    .getKey();
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
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM clients where id=:id";
            Client client = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Client.class);
            return client;
        }
    }

}
