package domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

public class Address {

    private int id;
    private String name;
    private String street;
    private String city;
    private Integer zipCode;
    private byte[] hash;
    private boolean deleted;
    private List<Cart> purchases;


    public Address() {}

    // базовый конструктор
    public Address(String name, String street, String city, Integer zipCode) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    // регистрация
    public Address(String name, String street, String city, Integer zipCode, String password) {
        this(name, street, city, zipCode);
        hash = calculateHash(password);
        deleted = false;
        purchases = new LinkedList<>();
    }

    // считывание из базы
    public Address(String name, String street, String city,
                   Integer zipCode, int id, byte[] hash, boolean deleted) {
        this(name, street, city, zipCode);
        this.id = id;
        if (hash != null) {
            this.hash = hash;
        } else {
            this.hash = calculateHash("cheese"); // default test-password
        }
        this.deleted = deleted;
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

    /*public String getHexHash() {
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString();
    }*/

    public String getHexHash(int width, String symbol) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (byte b : hash) {
            sb.append(String.format("%02X ", b));
            i++;
            if ((i % width == 0) & (i != hash.length))
                sb.append(symbol);
        }
        return sb.toString();
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
        return "Address [" + id + "]: " + name + ", " +
                street + ", " + city + ", " + zipCode +
                ", purchases: " + purchases.size() +
                (deleted ? " [deleted]" : "");
    }
}