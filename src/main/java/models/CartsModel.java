package models;

import dao.CartDAO;
import domain.Cart;
import org.apache.wicket.model.LoadableDetachableModel;

import java.util.List;

public class CartsModel extends LoadableDetachableModel {

    private CartDAO dao;

    public CartsModel(CartDAO dao) {
        super();
        this.dao = dao;
    }

    @Override
    protected Object load() {
        return getCarts();
    }

    protected List<Cart> getCarts() {
        return dao.getCartsList();
    }
}