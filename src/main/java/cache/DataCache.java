package cache;

import cache.iface.IDataCache;
import dao.iface.CommentDAO;
import dao.iface.DBConnection;
import domain.*;

import java.util.List;

/**
 * Created by Iwan on 14.20.3
 */
public class DataCache implements IDataCache {

    private AddressCache addressCache;
    private CartCache cartCache;
    private CartEntryCache cartEntryCache;
    private CheeseCache cheeseCache;
    private CommentDAO commentCache;

    public DataCache(DBConnection dbConnection) {
        // step 1
        cheeseCache = new CheeseCache(dbConnection.getCheeseDAO());
        // step 2
        addressCache = new AddressCache(dbConnection.getAddressDAO());
        cartEntryCache = new CartEntryCache(dbConnection.getCartEntryDAO(), cheeseCache);
        // step 3
        cartCache = new CartCache(dbConnection.getCartDAO(), cartEntryCache, addressCache);

        // no step :) // TODO :)
        commentCache = new CommentCache(dbConnection.getCommentDAO());
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
    public Address getAddress(long id) {
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
    public void insertCartEntry(Cart cart, CartEntry cheese) {
        cartEntryCache.insertCartEntry(cart, cheese);
    }

    /*@Override
    public List<CartEntry> getCartEntries(Cart cart) {
        return cartEntryCache.getCartEntries(cart);
    }*/

    @Override
    public List<CartEntry> getCartEntries(long cartId) {
        return cartEntryCache.getCartEntries(cartId);
    }


    // CheeseDAO

    @Override
    public List<Cheese> getCheesesList() {
        return cheeseCache.getCheesesList();
    }

    @Override
    public Cheese getCheese(long id) {
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

    @Override
    public List<Cheese> searchCheeseByName(String key) {
        return cheeseCache.searchCheeseByName(key);
    }

    @Override
    public List<Cheese> searchCheeseByDescription(String key) {
        return cheeseCache.searchCheeseByDescription(key);
    }


    // CommentDAO

    @Override
    public List<Comment> getCommentsList(Cheese cheese) {
        return commentCache.getCommentsList(cheese);
    }

    @Override
    public void insertComment(Comment comment, Cheese cheese, Address address) {
        commentCache.insertComment(comment, cheese, address);
    }


    // LikeDAO

    @Override
    public List<Address> getLikesList(Cheese cheese) {
        return null;
    }

    @Override
    public boolean exists(Like like) {
        return false;
    }

    @Override
    public void insertLike(Like like) {

    }
}
