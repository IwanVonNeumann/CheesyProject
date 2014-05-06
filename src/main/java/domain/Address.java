package domain;

import java.util.LinkedList;
import java.util.List;

import static security.PasswordManager.calculateHash;
import static security.PasswordManager.passwordIsCorrect;

public class Address {

    private int id;
    private Title title;
    private String name;
    private String street;
    private String city;
    private Integer zipCode;
    private byte[] hash;
    private boolean deleted;
    private List<Cart> purchases;


    private final String defaultPassword = "cheese";


    public Address() {}

    // базовый конструктор
    public Address(Title title, String name, String street, String city, Integer zipCode) {
        this.title = title;
        this.name = name;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    // регистрация
    public Address(Title title, String name, String street, String city, Integer zipCode, String password) {
        this(title, name, street, city, zipCode);
        hash = calculateHash(password);
        deleted = false;
        purchases = new LinkedList<>();
    }

    // считывание из базы
    public Address(Title title, String name, String street, String city,
                   Integer zipCode, int id, byte[] hash, boolean deleted) {
        this(title, name, street, city, zipCode);
        this.id = id;
        this.deleted = deleted;
        setHash(hash);
    }


    public Title getTitle() {
        return title;
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

    public byte[] getHash() {
        return hash;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<Cart> getPurchases() {
        return purchases;
    }


    public void setTitle(Title title) {
        this.title = title;
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

    public void setHash(byte[] hash) {
        if (hash != null) {
            this.hash = hash;
        } else {
            this.hash = calculateHash(defaultPassword); // default test-password
        }
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setPurchases(List<Cart> purchases) {
        this.purchases = purchases;
    }


    public boolean correctHash(String password) {
        return passwordIsCorrect(password, hash);
    }

    public void delete() {
        setDeleted(true);
    }

    public void setPassword(String password) {
        this.hash = calculateHash(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        return name.equals(address.name);
    }

    @Override
    public String toString() {
        return "Address [" + id + "]: " + title + " " + name + ", " +
                street + ", " + city + ", " + zipCode +
                //", purchases: " + getPurchases().size() + // дергает ленивую инициализацию!
                (deleted ? " [deleted]" : "");
    }
}