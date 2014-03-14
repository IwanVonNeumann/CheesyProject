package dao.jdbc;

import dao.iface.*;

/**
 * Created by IRuskevich on 14.14.3.
 */
public class JDBCDAOSet implements DAOSet {

    private JDBCConnection connection;

    private CheeseDAO cheeseDAO;
    private AddressDAO addressDAO;
    private CartEntryDAO cartEntryDAO;
    private CartDAO cartDAO;

    public JDBCDAOSet() {
        connection = new JDBCConnection();
        cheeseDAO = new JDBCCheeseDAO(connection.getConnection());
        addressDAO = new JDBCAddressDAO(connection.getConnection());
        cartEntryDAO = new JDBCCartEntryDAO(connection.getConnection());
        cartDAO = new JDBCCartDAO(connection.getConnection());
    }

    @Override
    public AddressDAO getAddressDAO() {
        return addressDAO;
    }

    @Override
    public CartDAO geCartDAO() {
        return cartDAO;
    }

    @Override
    public CartEntryDAO geCartEntryDAO() {
        return cartEntryDAO;
    }

    @Override
    public CheeseDAO geCheeseDAO() {
        return cheeseDAO;
    }
}
