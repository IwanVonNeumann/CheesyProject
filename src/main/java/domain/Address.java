package domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

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
        if (hash != null) {
            this.hash = hash;
        } else {
            this.hash = calculateHash("cheese"); // default test-password
        }
        this.deleted = deleted;
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
        this.hash = hash;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setPurchases(List<Cart> purchases) {
        this.purchases = purchases;
    }


    // TODO refactor
    public boolean correctHash(String password) {
        if (hash == null) System.out.println("Hash is null!");
        boolean correct = true;
        byte[] pass = calculateHash(password);
        int k = pass.length;
        for (int i = 0; i < k; i++) {
            correct &= hash[i] == pass[i];
        }
        return correct;
    }

    public static byte[] calculateHash(String password) {
        byte[] hash;
        try {
            byte[] passwordBytes = password.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            hash = md.digest(passwordBytes);
            return hash;
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("No algorithm ex...");
            return null;
        }
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
                ", purchases: " + purchases.size() +
                (deleted ? " [deleted]" : "");
    }
}