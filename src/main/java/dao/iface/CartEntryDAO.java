package dao.iface;

import domain.Cart;
import domain.CartEntry;

import java.util.List;

/**
 * Created by IRuskevich on 14.12.3
 */
public interface CartEntryDAO {

    // List<CartEntry> getCartEntries(Cart cart);

    // какой из двух методов логичнее оставить?
    // или нужны оба?
    List<CartEntry> getCartEntries(int cartId);

    // возможно, стоит переделать на
    // void insertCartEntry(int cartId, CartEntry cheese);
    void insertCartEntry(Cart cart, CartEntry cheese);
}
