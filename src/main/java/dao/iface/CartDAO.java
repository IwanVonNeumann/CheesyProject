package dao.iface;

import domain.Address;
import domain.Cart;

import java.util.List;

/**
 * Created by IRuskevich on 14.12.3.
 */
public interface CartDAO {

    public List<Cart> getCartsList();

    public List<Cart> getCartsList(Address address);

    public void insertCart(Address address, Cart cart);
}
