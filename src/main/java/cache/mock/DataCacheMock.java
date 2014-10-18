package cache.mock;

import cache.iface.IDataCache;
import dao.iface.*;
import domain.*;

import java.util.List;
import java.util.Set;

/**
 * Created by IRuskevich on 20.03.2014
 */
public class DataCacheMock implements IDataCache {

    private AddressDAO addressDAO;
    private CartDAO cartDAO;
    private CartEntryDAO cartEntryDAO;
    private CheeseDAO cheeseDAO;
    private CommentDAO commentDAO;
    private LikeDAO likeDAO;

    /*public DataCacheMock(DBConnection dbConnection) {
        addressDAO = dbConnection.getAddressDAO();
        cartDAO = dbConnection.getCartDAO();
        cartEntryDAO = dbConnection.getCartEntryDAO();
        cheeseDAO = dbConnection.getCheeseDAO();
        commentDAO = dbConnection.getCommentDAO();
        likeDAO = dbConnection.getLikeDAO();
        System.out.println("Data Cache mock created...");
    }*/

    public DataCacheMock() {
    }

    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    public void setCartDAO(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    public void setCartEntryDAO(CartEntryDAO cartEntryDAO) {
        this.cartEntryDAO = cartEntryDAO;
    }

    public void setCheeseDAO(CheeseDAO cheeseDAO) {
        this.cheeseDAO = cheeseDAO;
    }

    public void setCommentDAO(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public void setLikeDAO(LikeDAO likeDAO) {
        this.likeDAO = likeDAO;
    }

    // AddressDAO

    @Override
    public List<Address> getAddressesList() {
        return addressDAO.getAddressesList();
    }

    @Override
    public boolean exists(Address address) {
        return addressDAO.exists(address);
    }

    @Override
    public Address getAddress(int id) {
        return addressDAO.getAddress(id);
    }

    @Override
    public Address getAddress(String name) {
        return addressDAO.getAddress(name);
    }

    @Override
    public void insertAddress(Address address) {
        addressDAO.insertAddress(address);
    }

    @Override
    public void updateAddress(Address address) {
        addressDAO.updateAddress(address);
    }

    @Override
    public void safeDeleteAddress(Address address) {
        addressDAO.safeDeleteAddress(address);
    }


    // CartDAO

    @Override
    public List<Cart> getCartsList() {
        return cartDAO.getCartsList();
    }

    @Override
    public List<Cart> getCartsList(Address address) {
        return cartDAO.getCartsList(address);
    }

    @Override
    public void insertCart(Cart cart) {
        cartDAO.insertCart(cart);
    }


    // CartEntryDAO

    /*@Override
    public List<CartEntry> getCartEntries(Cart cart) {
        return cartEntryDAO.getCartEntries(cart);
    }*/

    @Override
    public List<CartEntry> getCartEntries(int cartId) {
        return cartEntryDAO.getCartEntries(cartId);
    }

    @Override
    public void insertCartEntry(Cart cart, CartEntry cheese) {
        cartEntryDAO.insertCartEntry(cart, cheese);
    }


    // CheeseDAo

    @Override
    public List<Cheese> getCheesesList() {
        return cheeseDAO.getCheesesList();
    }

    @Override
    public Cheese getCheese(int id) {
        return cheeseDAO.getCheese(id);
    }

    @Override
    public void addCheese(Cheese cheese) {
        cheeseDAO.addCheese(cheese);
    }

    @Override
    public void updateCheese(Cheese cheese) {
        cheeseDAO.updateCheese(cheese);
    }

    @Override
    public void safeDeleteCheese(Cheese cheese) {
        cheeseDAO.safeDeleteCheese(cheese);
    }

    @Override
    public boolean exists(Cheese cheese) {
        return cheeseDAO.exists(cheese);
    }

    @Override
    public List<Cheese> searchCheeseByName(String key) {
        return cheeseDAO.searchCheeseByName(key);
    }

    @Override
    public List<Cheese> searchCheeseByDescription(String key) {
        return cheeseDAO.searchCheeseByDescription(key);
    }


    // CommentDAO

    @Override
    public List<Comment> getCommentsList(Cheese cheese) {
        return commentDAO.getCommentsList(cheese);
    }

    @Override
    public void insertComment(Comment comment, Cheese cheese, Address address) {
        commentDAO.insertComment(comment, cheese, address);
    }

    @Override
    public Set<Address> getLikesList(Cheese cheese) {
        return likeDAO.getLikesList(cheese);
    }

    @Override
    public void insertLike(Cheese cheese, Address address) {
        likeDAO.insertLike(cheese, address);
    }
}