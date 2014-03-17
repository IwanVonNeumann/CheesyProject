package domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Address {

    private String name;
    private String street;
    private String city;
    private Integer zipCode;
    private int id;
    private byte[] hash;
    private boolean deleted;

    private List<Cart> purchases; // for hibernate

    public Address(String name, String street, String city, Integer zipCode) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public Address(String name, String street, String city, Integer zipCode, int id) {
        this(name, street, city, zipCode);
        this.id = id;
        this.hash = calculateHash("cheese"); // default test-password
    }

    public Address(String name, String street, String city,
                   Integer zipCode, int id, byte[] hash) {
        this(name, street, city, zipCode, id);
        if (hash != null) {
            this.hash = hash;
        }
        else this.hash = calculateHash("cheese");
    }

    public Address(String name, String street, String city,
                   Integer zipCode, int id, byte[] hash, boolean deleted) {
        this(name, street, city, zipCode, id, hash);
        this.deleted = deleted;
    }

    public Address(String name, String street, String city, Integer zipCode, String password) {
        this(name, street, city, zipCode);
        hash = calculateHash(password);
    }

    public Address() {
        /*
        name = "default";
        street = "default";
        city = "default";
        zipCode = 101;*/
    }

    public boolean correctHash(String password) {
        if (hash == null) System.out.println("Hash is null!");
        boolean correct = true;
        byte[] pass = calculateHash(password);
        int k = pass.length;
        for(int i = 0; i < k; i++) {
            correct &= hash[i] == pass[i];
        }
        return correct;
    }

    public static byte[] calculateHash(String password) {
        byte[] hash = null;
        try {
            byte[] passwordBytes = password.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            hash = md.digest(passwordBytes);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("No algorithm ex...");
        } finally {
            return hash;
        }
    }

    public String getHexHash() {
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString();
    }

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



    public void setPassword(String password) {
        this.hash = calculateHash(password);
    }

    @Override
    public boolean equals(Object o) {
        Address that = (Address)o;
        return (this.id == that.id);
    }

    @Override
    public String toString() {
        return name + ", " + street + ", " + city + ", " + zipCode +
                (deleted ? " [deleted]" : "");
    }
}