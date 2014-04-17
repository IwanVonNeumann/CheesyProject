package domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class Cart implements Serializable {

    private int id;
    private List<CartEntry> cheeses;
    private Address address;
    private Timestamp time;

    // создание вместе с сессией
    public Cart() {
        cheeses = new LinkedList<>();
    }

    // цивилизованное извлечение из базы
    public Cart(int id, List<CartEntry> cheeses, Address address, Timestamp time) {
        this.id = id;
        this.cheeses = cheeses;
        this.address = address;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public List<CartEntry> getCheeses() {
        return cheeses;
    }

    public Address getAddress() {
        return address;
    }

    public Timestamp getTime() {
        return time;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setCheeses(List<CartEntry> cheeses) {
        this.cheeses = cheeses;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }



    public Double getTotal() {
        double total = 0;
        //System.out.println("Counting cost of Cart with ID " + id);
        //System.out.println("Cheeses total: " + cheeses.size());
        for(CartEntry cartEntry : cheeses) {
            total += cartEntry.getCost();
        }
        return total;
    }

    public void addCheese(Cheese cheese) {
        boolean found = false;
        for (CartEntry cheese1 : cheeses) {
            if (cheese1.getName().equals(cheese.getName())) {
                cheese1.incQuantity();
                found = true;
                break;
            }
        }
        if (!found) {
            cheeses.add(new CartEntry(cheese));
        }
    }

    public void removeCheese(Cheese cheese) {
        int n = cheeses.size();
        for (int i = 0; i < n; i++) {
            if(cheeses.get(i).getName().equals(cheese.getName())) {
                cheeses.remove(i);
                break;
            }
        }
    }

    public void order() {
        time = new Timestamp(System.currentTimeMillis());
    }

    public void reset() {
        cheeses.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;

        Cart cart = (Cart) o;

        return address.equals(cart.address) && timestampsEqual(time, cart.time);
    }

    private boolean timestampsEqual(Timestamp t1, Timestamp t2) {
        return Math.abs(t1.getTime() - t2.getTime()) < 1000;
    }

    @Override
    public String toString() {
        return "Cart: " + address.getName() + ", " + cheeses.size() + " items at " + time;
    }
}
