package dao.jdbc;

import dao.iface.*;

import java.sql.Connection;

/**
 * Created by IRuskevich on 14.14.3.
 */
public class JDBCConnection implements DBConnection {

    private CheeseDAO cheeseDAO;
    private AddressDAO addressDAO;
    private CartEntryDAO cartEntryDAO;
    private CartDAO cartDAO;

    public JDBCConnection(Connection connection) {
        cheeseDAO = new JDBCCheeseDAO(connection);
        addressDAO = new JDBCAddressDAO(connection);
        cartEntryDAO = new JDBCCartEntryDAO(connection);
        cartDAO = new JDBCCartDAO(connection);
    }

    @Override
    public AddressDAO getAddressDAO() {
        return addressDAO;
    }

    @Override
    public CartDAO getCartDAO() {
        return cartDAO;
    }

    @Override
    public CartEntryDAO getCartEntryDAO() {
        return cartEntryDAO;
    }

    @Override
    public CheeseDAO getCheeseDAO() {
        return cheeseDAO;
    }
}
