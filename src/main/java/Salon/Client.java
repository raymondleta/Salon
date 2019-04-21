package Salon;
import java.util.Date;

public class Client {
    private String name;
    private String phoneNumber;
    private String service;
    private String date;


    public Client(String name, String phoneNumber, String service, String date) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.service = service;
        this.date = date;

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

}
