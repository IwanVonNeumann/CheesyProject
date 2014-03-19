package domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Cart implements Serializable {

    private int id;
    private List<MultiCheese> cheeses = new ArrayList<MultiCheese>();
    private Address address;
    private Timestamp time;
    private int customerID;

    // создание вместе с сессией
    public Cart() {
        cheeses = new LinkedList<MultiCheese>();
    }

    // извлечение из базы
    public Cart(Timestamp time, int id, int customerID) {
        this.time = time;
        this.id = id;
        this.customerID = customerID;
    }

    // цивилизованное извлечение из базы
    public Cart(int id, List<MultiCheese> cheeses, Address address, Timestamp time) {
        this.id = id;
        this.cheeses = cheeses;
        this.address = address;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public List<MultiCheese> getCheeses() {
        return cheeses;
    }

    public Address getAddress() {
        return address;
    }

    public Timestamp getTime() {
        return time;
    }

    public int getCustomerID() {
        return customerID;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setCheeses(List<MultiCheese> cheeses) {
        this.cheeses = cheeses;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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

}
