package domain;

public class Address {

    private String name;
    private String street;
    private String city;
    private Integer zipCode;
    private int id;

    public Address(String name, String street, String city, Integer zipCode) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public Address(String name, String street, String city, Integer zipCode, int id) {
        this(name, street, city, zipCode);
        this.id = id;
    }

    public Address() {
        /*
        name = "default";
        street = "default";
        city = "default";
        zipCode = 101;*/
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public void setId(int id) {
        this.id = id;
    }
}