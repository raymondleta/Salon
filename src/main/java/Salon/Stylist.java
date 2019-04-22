package Salon;
import java.util.List;
import java.util.ArrayList;

public class Stylist {
    private String name;
    private String phoneNumber;
    private static List<Stylist> instances = new ArrayList<>();
    private int id;
    private List<Client> clients;

    public Stylist(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        instances.add(this);
        id = instances.size();
        clients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public static List<Stylist> all() {
        return instances;
    }
    public static void clear() {
        instances.clear();
    }
    public int getId() {
        return id;
    }
    public static Stylist find(int id) {
        try {
            return instances.get(id - 1);
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }
    }
    public List<Client> getClients() {
        return clients;
    }
    public void addClient(Client client) {
        clients.add(client);
    }
}
