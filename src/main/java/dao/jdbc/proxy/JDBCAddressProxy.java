package dao.jdbc.proxy;

import dao.jdbc.dao.JDBCCartDAO;
import domain.Address;
import domain.Cart;

import java.sql.Connection;
import java.util.List;

/**
 * Created by IRuskevich on 14.18.3
 */
public class JDBCAddressProxy extends Address {

    private Connection connection;

    // считывание из базы, запись создана программно
    public JDBCAddressProxy(String name, String street, String city,
                            Integer zipCode, int id, byte[] hash, boolean deleted,
                            Connection connection) {
        super(name, street, city, zipCode, id, hash, deleted);
        this.connection = connection;
    }

    @Override
    public List<Cart> getPurchases() {
        System.out.println("[JDBC] Address proxy call for " +
                getName() + "...");
        if (super.getPurchases() == null) {
            System.out.println("[JDBC] Running lazy init for purchases list...");
            super.setPurchases(
                    new JDBCCartDAO(connection).getCartsList(this));
        } else {
            System.out.println("[JDBC] Purchases list already in memory...");
        }
        return super.getPurchases();
    }
}