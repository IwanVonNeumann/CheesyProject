package dao.jdbc.proxy;

import dao.iface.CartDAO;
import domain.Address;
import domain.Cart;

import java.util.List;

/**
 * Created by IRuskevich on 14.18.3
 */

public class JDBCAddressProxy extends Address {

    private CartDAO cartDAO;

    // считывание из базы, запись создана программно
    public JDBCAddressProxy(Address address, CartDAO cartDAO) {
        super(
                address.getTitle(),
                address.getName(),
                address.getStreet(),
                address.getCity(),
                address.getZipCode(),
                address.getId(),
                address.getHash(),
                address.isDeleted());
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