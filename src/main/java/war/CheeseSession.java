package war;

/* нельзя просто dao.*; !!!
import dao.DBConnection;
import dao.AddressDAO;
import dao.CartEntryDAO;
import dao.CheeseDAO;
import dao.CartDAO;
*/

import dao.DBConnection;
import dao.AddressDAO;
import dao.CartEntryDAO;
import dao.CheeseDAO;
import dao.CartDAO;

import domain.Address;
import domain.Cart;
import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

public class CheeseSession extends WebSession {

    private Cart cart;
    private DBConnection connection;

    private CheeseDAO cheeseDAO;
    private AddressDAO addressDAO;
    private CartEntryDAO cartEntryDAO;
    private CartDAO cartDAO;

    private Address address;

    protected CheeseSession(Request request) {
        super(request);
        cart = new Cart();
        connection = new DBConnection();

        cheeseDAO = new CheeseDAO(connection.getConnection());
        addressDAO = new AddressDAO(connection.getConnection());
        cartEntryDAO = new CartEntryDAO(connection.getConnection());
        cartDAO = new CartDAO(connection.getConnection());
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
