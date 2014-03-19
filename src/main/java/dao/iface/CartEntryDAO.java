package dao.iface;

import domain.Cart;
import domain.MultiCheese;

import java.util.List;

/**
 * Created by IRuskevich on 14.12.3.
 */
public interface CartEntryDAO {

    // TODO сделать каскадным
    // MultiCheese никогда сам по себе не добавляется, а только в составе корзины
    void insertCartEntry(Cart cart, MultiCheese cheese);

    List<MultiCheese> getCartEntries(Cart cart);

    List<MultiCheese> getCartEntries(int cartId);
}
