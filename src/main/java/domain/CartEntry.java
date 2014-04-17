package domain;

import java.io.Serializable;

public class CartEntry implements Serializable { // какого?

    private int id;
    private Cheese cheese;
    private int quantity;

    public CartEntry() {}

    // используется при создании из Cheese в корзине
    public CartEntry(Cheese cheese) {
        this.cheese = cheese;
        quantity = 1;
    }

    public CartEntry(Cheese cheese, int quantity) {
        this(cheese);
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public Cheese getCheese() {
        return cheese;
    }

    public int getQuantity() {
        return quantity;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setCheese(Cheese cheese) {
        this.cheese = cheese;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public int getCheeseId() {
        return cheese.getId();
    }

    public String getName() {
        return cheese.getName();
    }

    public Double getPrice() {
        return cheese.getPrice();
    }



    public Double getCost() {
        return quantity * cheese.getPrice();
    }

    public void incQuantity() {
        quantity += 1;
    }

    public void decQuantity() {
        if (quantity > 1)
            quantity -= 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartEntry)) return false;

        CartEntry cheese1 = (CartEntry) o;

        if (quantity != cheese1.quantity) return false;

        return cheese.equals(cheese1.cheese);
    }

    @Override
    public String toString() {
        return "CartEntry [" + id + "]: {" +
                "Cheese [" + cheese.getId() + "]: " + cheese.getName() +
                ", quantity = " + quantity +
                '}';
    }
}