package war;

/*
// компилится :)
import dao.iface.DBConnection;
import dao.iface.AddressDAO;
import dao.iface.CartDAO;
import dao.iface.CartEntryDAO;
import dao.iface.CheeseDAO;
// не компилится :(
import dao.iface.*;
*/

import dao.iface.DBConnection;
import dao.iface.AddressDAO;
import dao.iface.CartDAO;
import dao.iface.CartEntryDAO;
import dao.iface.CheeseDAO;

import domain.Address;
import domain.Cart;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

public class CheeseSession extends WebSession {

    private Cart cart;
    private Address address;

    private DBConnection connection;

    protected CheeseSession(Request request, DBConnection connection) {
        super(request);

        cart = new Cart();
        this.connection = connection;
    }

    public Cart getCart() {
        return cart;
    }

    public CheeseDAO getCheeseDAO() {
        return connection.getCheeseDAO();
    }

    public AddressDAO getAddressDAO() {
        return connection.getAddressDAO();
    }

    public CartEntryDAO getCartEntryDAO() {
        return connection.getCartEntryDAO();
    }

    public CartDAO getCartDAO() {
        return connection.getCartDAO();
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
