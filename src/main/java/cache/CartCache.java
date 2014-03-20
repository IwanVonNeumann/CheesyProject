package cache;

import dao.iface.CartDAO;
import domain.Address;
import domain.Cart;

import java.util.List;

/**
 * Created by Iwan on 14.20.3.
 */
public class CartCache implements CartDAO {

    private CartDAO cartDAO;

    public CartCache(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    @Override
    public List<Cart> getCartsList() {
        return cartDAO.getCartsList();
    }

    @Override
    public List<Cart> getCartsList(Address address) {
        return cartDAO.getCartsList(address);
    }

    @Override
    public void insertCart(Cart cart) {
        cartDAO.insertCart(cart);
    }
}
