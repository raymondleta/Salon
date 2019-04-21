package Salon;

public class Client {
    private String name;
    private String phoneNumber;
    private String service;

    public Client(String name, String phoneNumber, String service) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.service = service;
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

}
