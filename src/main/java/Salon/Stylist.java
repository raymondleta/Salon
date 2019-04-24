package Salon;
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Stylist {
    private String name;
    private String phoneNumber;
    private int id;
    private List<Client> clients;

    public Stylist(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        clients = new ArrayList<>();
    }
    public static List<Stylist> all() {
        String sql = "SELECT id, name, phoneNumber FROM stylists";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Stylist.class);
        }
    }
    @Override
    public boolean equals(Object otherStylist) {
        if (!(otherStylist instanceof Stylist)) {
            return false;
        } else {
            Stylist newStylist = (Stylist) otherStylist;
            return this.getName().equals(newStylist.getName()) &&
                    this.getId() == newStylist.getId() &&
                    this.getPhoneNumber().equals(newStylist.getPhoneNumber());
        }
    }
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO stylists (name, phoneNumber) VALUES (:name, :phoneNumber)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("phoneNumber", this.phoneNumber)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static Stylist find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM stylists where id=:id";
            Stylist stylist = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Stylist.class);
            return stylist;
        }
    }
    public List<Client> getClients() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM clients where stylistId=:id";
            return con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeAndFetch(Client.class);
        }
    }
    public void update(String name, String phoneNumber) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE stylists SET name = :name, phoneNumber = :phoneNumber WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("phoneNumber", phoneNumber)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }




    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public int getId() {
        return id;
    }


    public void addClient(Client client) {
        clients.add(client);
    }
}
