package Salon;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private String phoneNumber;
    private String service;
    private String date;
    private static List<Client> instances = new ArrayList<>();
    private int id;


    public Client(String name, String phoneNumber, String service, String date) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.service = service;
        this.date = date;
        instances.add(this);
        id = instances.size();

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
    public static List<Client> all() {
        return instances;
    }
    public static void clear() {
        instances.clear();
    }
    public int getId() {
        return id;
    }
    public static Client find(int id) {
        return instances.get(id - 1);
    }

}
