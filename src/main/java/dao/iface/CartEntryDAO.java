package dao.iface;

import domain.Cart;
import domain.MultiCheese;

import java.util.List;

/**
 * Created by IRuskevich on 14.12.3
 */
public interface CartEntryDAO {

    // List<MultiCheese> getCartEntries(Cart cart);

    // какой из двух методов логичнее оставить?
    // или нужны оба?
    List<MultiCheese> getCartEntries(int cartId);

    // возможно, стоит переделать на
    // void insertCartEntry(int cartId, MultiCheese cheese);
    void insertCartEntry(Cart cart, MultiCheese cheese);
}
