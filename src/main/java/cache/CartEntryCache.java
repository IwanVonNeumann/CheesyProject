package cache;

import cache.iface.IDataCache;
import dao.iface.CartEntryDAO;
import domain.Cart;
import domain.MultiCheese;

import java.util.List;

/**
 * Created by Iwan on 14.20.3
 */
public class CartEntryCache implements CartEntryDAO {

    private CartEntryDAO cartEntryDAO;

    private IDataCache dataCache;

    public CartEntryCache(IDataCache dataCache, CartEntryDAO cartEntryDAO) {
        this.dataCache = dataCache;
        this.cartEntryDAO = cartEntryDAO;
    }

    /*@Override
    public List<MultiCheese> getCartEntries(Cart cart) {
        return cartEntryDAO.getCartEntries(cart);
    }*/

    @Override
    public List<MultiCheese> getCartEntries(int cartId) {
        return cartEntryDAO.getCartEntries(cartId);
    }

    @Override
    public void insertCartEntry(Cart cart, MultiCheese cheese) {
        cartEntryDAO.insertCartEntry(cart, cheese);
    }
}