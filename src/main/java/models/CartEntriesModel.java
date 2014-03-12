package models;

import dao.jdbc.JDBCCartEntryDAO;
import domain.Cart;
import domain.MultiCheese;
import org.apache.wicket.model.LoadableDetachableModel;

import java.util.List;

public class CartEntriesModel extends LoadableDetachableModel {

    private JDBCCartEntryDAO dao;
    private Cart cart;

    public CartEntriesModel(JDBCCartEntryDAO dao, Cart cart) {
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
