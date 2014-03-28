package cache;

import dao.iface.AddressDAO;
import dao.iface.CartDAO;
import dao.iface.CartEntryDAO;
import domain.Address;
import domain.Cart;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Iwan on 14.20.3
 */
public class CartCache implements CartDAO {

    private CartDAO cartDAO;
    private CartEntryDAO cartEntryDAO;
    private AddressDAO addressDAO;

    private List<Cart> carts;

    public CartCache(CartDAO cartDAO, CartEntryDAO cartEntryDAO, AddressDAO addressDAO) {
        this.cartDAO = cartDAO;
        this.cartEntryDAO = cartEntryDAO;
        this.addressDAO = addressDAO;
        carts = new LinkedList<>(cartDAO.getCartsList());
    }

    @Override
    public List<Cart> getCartsList() {
        return carts;
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
