package models;

import dao.iface.CartEntryDAO;
import domain.Cart;
import domain.MultiCheese;
import org.apache.wicket.model.LoadableDetachableModel;

import java.util.List;

public class CartEntriesModel extends LoadableDetachableModel {

    private CartEntryDAO dao;
    private Cart cart;

    public CartEntriesModel(CartEntryDAO dao, Cart cart) {
        this.dao = dao;
        this.cart = cart;
    }

    @Override
    protected Object load() {
        return getCartEntries();
    }

    // было protected...
    // используется при cart.setCheeses() из ViewPurchases
    // возможно, неэффективно: не используется свойство Loadable/Detachable
    /*  сменим обратно =)
    public List<MultiCheese> getCartEntries() {
        return dao.getCartEntries(cart);
    }
    */

    protected List<MultiCheese> getCartEntries() {
        return dao.getCartEntries(cart);
    }
}
