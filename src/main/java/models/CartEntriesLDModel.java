package models;

import cache.iface.IDataCache;

// TODO: пересмотреть: возможно, класс не нужен
public class CartEntriesLDModel extends AbstractLDModel {

    public CartEntriesLDModel(IDataCache dataCache) {
        super(dataCache);
    }

    /*private CartEntryDAO dao;
    private Cart cart;

    public CartEntriesLDModel(CartEntryDAO dao, Cart cart) {
        this.dao = dao;
        this.cart = cart;
    }*/

    @Override
    protected Object load() {
        //return getCartEntries();
        return null;
    }

    // давно закомментировано :)
    // было protected...
    // используется при cart.setEntries() из ViewPurchases
    // возможно, неэффективно: не используется свойство Loadable/Detachable
    /*  сменим обратно =)
    public List<CartEntry> getCartEntries() {
        return dao.getCartEntries(cart);
    }
    */

    /*protected List<CartEntry> getCartEntries() {
        return dao.getCartEntries(cart);
    }*/
}
