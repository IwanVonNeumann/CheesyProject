package dao.jdbc.proxy;

import dao.iface.CartDAO;
import domain.Address;
import domain.Cart;
import domain.Title;

import java.util.List;

/**
 * Created by IRuskevich on 14.18.3
 */

public class JDBCAddressProxy extends Address {

    private CartDAO cartDAO;

    // считывание из базы, запись создана программно
    public JDBCAddressProxy(Title title, String name, String street, String city,
                            Integer zipCode, int id, byte[] hash, boolean deleted,
                            CartDAO cartDAO) {
        super(title, name, street, city, zipCode, id, hash, deleted);
        this.cartDAO = cartDAO;
    }

    @Override
    public List<Cart> getPurchases() {
        System.out.println("[JDBC] Address proxy call for " +
                getName() + "...");
        if (super.getPurchases() == null) {
            System.out.println("[JDBC] Running lazy init for purchases list...");
            super.setPurchases(cartDAO.getCartsList(this));
        } else {
            System.out.println("[JDBC] Purchases list already in memory...");
        }
        return super.getPurchases();
    }
}