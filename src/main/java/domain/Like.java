package domain;

/**
 * Created by Iwan on 26.10.2014
 */

public class Like {

    private long id;

    private Address address;

    private Cheese cheese;


    public Like() {
    }

    public Like(Cheese cheese, Address address) {
        this.cheese = cheese;
        this.address = address;
    }


    public long getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public Cheese getCheese() {
        return cheese;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setCheese(Cheese cheese) {
        this.cheese = cheese;
    }
}
