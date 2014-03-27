package cache;

import cache.iface.IDataCache;
import dao.iface.CartDAO;
import domain.Address;
import domain.Cart;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Iwan on 14.20.3
 */
public class CartCache implements CartDAO {

    private CartDAO cartDAO;

    private IDataCache dataCache;

    private List<Cart> carts;

    public CartCache(IDataCache dataCache, CartDAO cartDAO) {
        this.dataCache = dataCache;
        this.cartDAO = cartDAO;
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
