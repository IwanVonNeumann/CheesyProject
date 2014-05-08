package domain;

import java.sql.Timestamp;

/**
 * Created by IRuskevich on 08.05.2014
 */
public class Comment {

    private int id;
    private String text;
    private Timestamp time;
    private Address address;

    public Comment() {
    }

    public Comment(int id, String text, Timestamp time, Address address) {
        this.id = id;
        this.text = text;
        this.time = time;
        this.address = address;
    }



    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Timestamp getTime() {
        return time;
    }

    public Address getAddress() {
        return address;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "text='" + text + '\'' +
                ", time=" + time +
                ", address=" + address +
                '}';
    }
}