package models;

import dao.iface.CartDAO;
import domain.Address;
import domain.Cart;
import org.apache.wicket.model.LoadableDetachableModel;

import java.util.List;

public class CartsLDModel extends LoadableDetachableModel {

    private CartDAO dao;
    private Address address;

    public CartsLDModel(CartDAO dao) {
        super();
        this.dao = dao;
        address = null;
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
            cartsList = dao.getCartsList();
        } else {
            cartsList = dao.getCartsList(address);
        }
        return cartsList;
    }
}