package domain;

import java.io.Serializable;

public class MultiCheese implements Serializable { // какого?

    private int id;
    private Cheese cheese;
    private int cheeseId;
    private int quantity;
    private int cartId;

    public MultiCheese() {
    }

    public MultiCheese(Cheese cheese) {
        this.cheese = cheese;
        cheeseId = cheese.getId();
        quantity = 1;
    }

    public MultiCheese(Cheese cheese, int cartId) {
        this(cheese);
        this.cartId = cartId;
    }

    public MultiCheese(Cheese cheese, int quantity, int cartId) {
        this(cheese, cartId);
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public Cheese getCheese() {
        return cheese;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCartId() {
        return cartId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCheese(Cheese cheese) {
        this.cheese = cheese;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
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
}