package dao.iface;

import domain.Cart;
import domain.CartEntry;

import java.util.List;

/**
 * Created by IRuskevich on 14.12.3
 */

public interface CartEntryDAO {

    List<CartEntry> getCartEntries(long cartId);

    // возможно, стоит переделать на
    // void insertCartEntry(int cartId, CartEntry cheese);
    // или на insert(Cart cart)
    void insertCartEntry(Cart cart, CartEntry cheese);
}
