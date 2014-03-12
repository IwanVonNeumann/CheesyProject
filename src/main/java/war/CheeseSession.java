package war;

/* нельзя просто dao.*; !!!
import dao.jdbc.JDBCConnection;
import dao.jdbc.JDBCAddressDAO;
import dao.jdbc.JDBCCartEntryDAO;
import dao.jdbc.JDBCCheeseDAO;
import dao.jdbc.JDBCCartDAO;
*/

import dao.iface.AddressDAO;
import dao.iface.CartDAO;
import dao.iface.CartEntryDAO;
import dao.iface.CheeseDAO;

import dao.jdbc.JDBCConnection;
import dao.jdbc.JDBCAddressDAO;
import dao.jdbc.JDBCCartEntryDAO;
import dao.jdbc.JDBCCheeseDAO;
import dao.jdbc.JDBCCartDAO;

import domain.Address;
import domain.Cart;
import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

public class CheeseSession extends WebSession {

    private Cart cart;
    JDBCConnection connection = new JDBCConnection();

    private CheeseDAO cheeseDAO;
    private AddressDAO addressDAO;
    private CartEntryDAO cartEntryDAO;
    private CartDAO cartDAO;

    private Address address;

    protected CheeseSession(Request request) {
        super(request);
        cart = new Cart();

        cheeseDAO = new JDBCCheeseDAO(connection.getConnection());
        addressDAO = new JDBCAddressDAO(connection.getConnection());
        cartEntryDAO = new JDBCCartEntryDAO(connection.getConnection());
        cartDAO = new JDBCCartDAO(connection.getConnection());
    }

    public Cart getCart() {
        return cart;
    }

    public CheeseDAO getCheeseDAO() {
        return cheeseDAO;
    }

    public AddressDAO getAddressDAO() {
        return addressDAO;
    }

    public CartEntryDAO getCartEntryDAO() {
        return cartEntryDAO;
    }

    public CartDAO getCartDAO() {
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
