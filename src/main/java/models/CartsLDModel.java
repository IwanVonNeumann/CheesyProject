package models;

import cache.iface.IDataCache;
import domain.Address;
import domain.Cart;

import java.util.List;

public class CartsLDModel extends AbstractLDModel {

    private Address address;

    public CartsLDModel(IDataCache dataCache, Address address) {
        super(dataCache);
        this.address = address;
    }

    public CartsLDModel(IDataCache dataCache) {
        this(dataCache, null);
    }

/*    public CartsLDModel(CartDAO dao, Address address) {
        this(dao);
        this.address = address;
    }*/

    @Override
    protected Object load() {
        return getCarts();
    }

    protected List<Cart> getCarts() {
        List<Cart> cartsList;
        if (address == null) {
            cartsList = dataCache.getCartsList();
        } else {
            cartsList = dataCache.getCartsList(address);
        }
        return cartsList;
    }
}