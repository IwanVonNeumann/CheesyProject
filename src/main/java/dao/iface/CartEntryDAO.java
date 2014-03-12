package dao.iface;

import domain.Cart;
import domain.MultiCheese;

import java.util.List;

/**
 * Created by IRuskevich on 14.12.3.
 */
public interface CartEntryDAO {

    public void insertCartEntry(Cart cart, MultiCheese cheese);

    public List<MultiCheese> getCartEntries(Cart cart);
}
