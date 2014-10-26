package cache;

import dao.iface.CartEntryDAO;
import dao.iface.CheeseDAO;
import domain.Cart;
import domain.CartEntry;

import java.util.List;

/**
 * Created by Iwan on 14.20.3
 */
public class CartEntryCache implements CartEntryDAO {

    private CartEntryDAO cartEntryDAO;
    private CheeseDAO cheeseDAO;

    public CartEntryCache(CartEntryDAO cartEntryDAO, CheeseDAO cheeseDAO) {
        this.cartEntryDAO = cartEntryDAO;
        this.cheeseDAO = cheeseDAO;
    }

    /*@Override
    public List<CartEntry> getCartEntries(Cart cart) {
        return cartEntryDAO.getCartEntries(cart);
    }*/

    @Override
    public List<CartEntry> getCartEntries(long cartId) {
        return cartEntryDAO.getCartEntries(cartId);
    }

    @Override
    public void insertCartEntry(Cart cart, CartEntry cheese) {
        cartEntryDAO.insertCartEntry(cart, cheese);
    }
}