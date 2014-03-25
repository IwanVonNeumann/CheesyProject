package domain;

import java.io.Serializable;

public class MultiCheese implements Serializable { // какого?

    private int id;
    private Cheese cheese;
    private int quantity;

    public MultiCheese() {}

    // используется при создании из Cheese в корзине
    public MultiCheese(Cheese cheese) {
        this.cheese = cheese;
        quantity = 1;
    }

    public MultiCheese(Cheese cheese, int quantity) {
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
        if (!(o instanceof MultiCheese)) return false;

        MultiCheese cheese1 = (MultiCheese) o;

        if (quantity != cheese1.quantity) return false;

        return cheese.equals(cheese1.cheese);
    }
}