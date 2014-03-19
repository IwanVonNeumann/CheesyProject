package dao.iface;

import domain.Address;
import domain.Cart;

import java.util.List;

/**
 * Created by IRuskevich on 14.12.3.
 */
public interface CartDAO {

    List<Cart> getCartsList();

    List<Cart> getCartsList(Address address);

    void insertCart(Cart cart);
}
