package net.eazyhealth.id.app.model.response.backendless;

/**
 * Created by GALIH ADITYO on 4/10/2016.
 */
public class Clinic extends BackendlessObject {

    private String address;
    private String city;
    private String name;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Clinic() {
    }
}
