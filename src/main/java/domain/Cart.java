package domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {

    private List<MultiCheese> cheeses = new ArrayList<MultiCheese>();
    private Address billingAddress = new Address();
    private Timestamp time;
    private int id;
    private int customerID;

    public Cart() {
    }

    public Cart(Timestamp time, int id, int customerID) {
        this.time = time;
        this.id = id;
        this.customerID = customerID;
    }

    // зачем??
    public List<MultiCheese> getCheeses() {
        return cheeses;
    }

    // Если понадобится - написать
    // понадобилось =)
    public void setCheeses(List<MultiCheese> other) {
        cheeses = other;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address other) {
        billingAddress = other;
    }

    public Double getTotal() {
        double total = 0;
        //System.out.println("Counting cost of Cart with ID " + id);
        //System.out.println("Cheeses total: " + cheeses.size());
        for(MultiCheese multiCheese : cheeses) {
            total += multiCheese.getCost();
        }
        return total;
    }

    public void addCheese(Cheese cheese) {
        boolean found = false;
        for (MultiCheese cheese1 : cheeses) {
            if (cheese1.getName().equals(cheese.getName())) {
                cheese1.incQuantity();
                found = true;
                break;
            }
        }
        if (!found) {
            cheeses.add(new MultiCheese(cheese));
        }
    }

    public void removeCheese(MultiCheese cheese) {
        int n = cheeses.size();
        for (int i = 0; i < n; i++) {
            if(cheeses.get(i).getName().equals(cheese.getName())) {
                cheeses.remove(i);
                break;
            }
        }
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
