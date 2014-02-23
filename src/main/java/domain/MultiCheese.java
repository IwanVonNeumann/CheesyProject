package domain;

import java.io.Serializable;

public class MultiCheese implements Serializable { // какого?

    private Cheese cheese;
    private int quantity;
    private int cartId;

    public MultiCheese(Cheese cheese) {
        this.cheese = cheese;
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

    public Cheese getCheese() {
        return cheese;
    }

    public String getName() {
        return cheese.getName();
    }

    public Double getPrice() {
        return cheese.getPrice();
    }

    public int getId() {
        return cheese.getId();
    }

    public int getCartId() {
        return cartId;
    }



    public void setCheese(Cheese cheese) {
        this.cheese = cheese;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getQuantity() {
        return quantity;
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
