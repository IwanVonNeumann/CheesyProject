package cache;

import cache.iface.IDataCache;

import dao.iface.DBConnection;
import domain.Address;
import domain.Cart;
import domain.Cheese;
import domain.MultiCheese;

import java.util.List;

/**
 * Created by Iwan on 14.20.3
 */
public class DataCache implements IDataCache {

    private AddressCache addressCache;
    private CartCache cartCache;
    private CartEntryCache cartEntryCache;
    private CheeseCache cheeseCache;

    public DataCache(DBConnection dbConnection) {
        // step 1
        cheeseCache = new CheeseCache(this, dbConnection.getCheeseDAO());
        // step 2
        addressCache = new AddressCache(this, dbConnection.getAddressDAO());
        cartEntryCache = new CartEntryCache(this, dbConnection.getCartEntryDAO());
        // step 3
        cartCache = new CartCache(this, dbConnection.getCartDAO());
    }

    // AddressDAO

    @Override
    public List<Address> getAddressesList() {
        return addressCache.getAddressesList();
    }

    @Override
    public boolean exists(Address address) {
        return addressCache.exists(address);
    }

    @Override
    public Address getAddress(int id) {
        return addressCache.getAddress(id);
    }

    @Override
    public Address getAddress(String name) {
        return addressCache.getAddress(name);
    }

    @Override
    public void insertAddress(Address address) {
        addressCache.insertAddress(address);
    }

    @Override
    public void updateAddress(Address address) {
        addressCache.updateAddress(address);
    }

    @Override
    public void safeDeleteAddress(Address address) {
        addressCache.safeDeleteAddress(address);
    }


    // CartDAO

    @Override
    public List<Cart> getCartsList() {
        return cartCache.getCartsList();
    }

    @Override
    public List<Cart> getCartsList(Address address) {
        return cartCache.getCartsList(address);
    }

    @Override
    public void insertCart(Cart cart) {
        cartCache.insertCart(cart);
    }



    // CartEntryDAO

    @Override
    public void insertCartEntry(Cart cart, MultiCheese cheese) {
        cartEntryCache.insertCartEntry(cart, cheese);
    }

    /*@Override
    public List<MultiCheese> getCartEntries(Cart cart) {
        return cartEntryCache.getCartEntries(cart);
    }*/

    @Override
    public List<MultiCheese> getCartEntries(int cartId) {
        return cartEntryCache.getCartEntries(cartId);
    }


    // CheeseDAO

    @Override
    public List<Cheese> getCheesesList() {
        return cheeseCache.getCheesesList();
    }

    @Override
    public Cheese getCheese(int id) {
        return cheeseCache.getCheese(id);
    }

    @Override
    public void addCheese(Cheese cheese) {
        cheeseCache.addCheese(cheese);
    }

    @Override
    public void updateCheese(Cheese cheese) {
        cheeseCache.updateCheese(cheese);
    }

    @Override
    public void safeDeleteCheese(Cheese cheese) {
        cheeseCache.safeDeleteCheese(cheese);
    }

    @Override
    public boolean exists(Cheese cheese) {
        return cheeseCache.exists(cheese);
    }

}
