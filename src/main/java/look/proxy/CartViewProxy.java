package look.proxy;

import domain.Cart;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by IRuskevich on 08.05.2014
 */
public class CartViewProxy {

    private Cart cart;

    public CartViewProxy(Cart cart) {
        this.cart = cart;
    }


    public String getCustomer() {
        return cart.getAddress().getName();
    }

    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        return dateFormat.format(cart.getTime());
    }

    public String getTime() {
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return timeFormat.format(cart.getTime());
    }
}
