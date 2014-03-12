package war;

/* нельзя просто dao.*; !!!
import dao.jdbc.JDBCConnection;
import dao.jdbc.JDBCAddressDAO;
import dao.jdbc.JDBCCartEntryDAO;
import dao.jdbc.JDBCCheeseDAO;
import dao.jdbc.JDBCCartDAO;
*/

import dao.jdbc.*;

import domain.Address;
import domain.Cart;
import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

public class CheeseSession extends WebSession {

    private Cart cart;
    private JDBCConnection connection;

    private JDBCCheeseDAO cheeseDAO;
    private JDBCAddressDAO addressDAO;
    private JDBCCartEntryDAO cartEntryDAO;
    private JDBCCartDAO cartDAO;

    private Address address;

    protected CheeseSession(Request request) {
        super(request);
        cart = new Cart();
        connection = new JDBCConnection();

        cheeseDAO = new JDBCCheeseDAO(connection.getConnection());
        addressDAO = new JDBCAddressDAO(connection.getConnection());
        cartEntryDAO = new JDBCCartEntryDAO(connection.getConnection());
        cartDAO = new JDBCCartDAO(connection.getConnection());
    }

    public Cart getCart() {
        return cart;
    }

    public JDBCCheeseDAO getCheeseDAO() {
        return cheeseDAO;
    }

    public JDBCAddressDAO getAddressDAO() {
        return addressDAO;
    }

    public JDBCCartEntryDAO getCartEntryDAO() {
        return cartEntryDAO;
    }

    public JDBCCartDAO getCartDAO() {
        return cartDAO;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void logout() {
        invalidate();
        //address = null;
        //cart = new Cart();
    }
}
