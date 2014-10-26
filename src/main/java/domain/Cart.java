package domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class Cart implements Serializable {

    private long id;
    private List<CartEntry> entries;
    private Address address;
    private Timestamp time;

    // создание вместе с сессией
    public Cart() {
        entries = new LinkedList<>();
    }

    // цивилизованное извлечение из базы
    public Cart(int id, List<CartEntry> entries, Address address, Timestamp time) {
        this.id = id;
        this.entries = entries;
        this.address = address;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public List<CartEntry> getEntries() {
        return entries;
    }

    public Address getAddress() {
        return address;
    }

    public Timestamp getTime() {
        return time;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setEntries(List<CartEntry> cheeses) {
        this.entries = cheeses;
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
        for(CartEntry cartEntry : entries) {
            total += cartEntry.getCost();
        }
        return total;
    }

    public void addCheese(Cheese cheese) {
        boolean found = false;
        for (CartEntry cheese1 : entries) {
            if (cheese1.getName().equals(cheese.getName())) {
                cheese1.incQuantity();
                found = true;
                break;
            }
        }
        if (!found) {
            entries.add(new CartEntry(cheese));
        }
    }

    public void removeCheese(Cheese cheese) {
        int n = entries.size();
        for (int i = 0; i < n; i++) {
            if(entries.get(i).getName().equals(cheese.getName())) {
                entries.remove(i);
                break;
            }
        }
    }

    public void order() {
        time = new Timestamp(System.currentTimeMillis());
    }

    public void reset() {
        entries.clear();
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
        StringBuilder result = new StringBuilder(
                "Cart [" + id + "]: " + address.getName() + " [" + address.getId() + "], " +
                        entries.size() + " items at " + time);
        result.append(":\n");
        for (CartEntry cartEntry : entries) {
            result.append("\t");
            result.append(cartEntry.toString());
            result.append("\n");
        }
        return result.toString();
    }
}
