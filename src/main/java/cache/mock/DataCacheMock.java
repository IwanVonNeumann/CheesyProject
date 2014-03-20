package cache.mock;

import cache.iface.IDataCache;

import dao.iface.*;

import domain.Address;
import domain.Cart;
import domain.Cheese;
import domain.MultiCheese;

import java.util.List;

/**
 * Created by IRuskevich on 20.03.2014
 */
public class DataCacheMock implements IDataCache {

    private AddressDAO addressDAO;
    private CartDAO cartDAO;
    private CartEntryDAO cartEntryDAO;
    private CheeseDAO cheeseDAO;

    public DataCacheMock(DBConnection dbConnection) {
        addressDAO = dbConnection.getAddressDAO();
        cartDAO = dbConnection.getCartDAO();
        cartEntryDAO = dbConnection.getCartEntryDAO();
        cheeseDAO = dbConnection.getCheeseDAO();
    }


    // AddressDAO

    @Override
    public List<Address> getAddressesList() {
        return addressDAO.getAddressesList();
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

    @Override
    public List<MultiCheese> getCartEntries(Cart cart) {
        return cartEntryDAO.getCartEntries(cart);
    }

    @Override
    public List<MultiCheese> getCartEntries(int cartId) {
        return cartEntryDAO.getCartEntries(cartId);
    }

    @Override
    public void insertCartEntry(Cart cart, MultiCheese cheese) {
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
}
