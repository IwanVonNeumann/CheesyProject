package war;

import dao.iface.*;

import domain.Address;
import domain.Cart;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

public class CheeseSession extends WebSession {

    private Cart cart;
    private Address address;

    private DAOSet daoSet;

    protected CheeseSession(Request request, DAOSet daoSet) {
        super(request);

        cart = new Cart();
        this.daoSet = daoSet;
    }

    public Cart getCart() {
        return cart;
    }

    public CheeseDAO getCheeseDAO() {
        return daoSet.geCheeseDAO();
    }

    public AddressDAO getAddressDAO() {
        return daoSet.getAddressDAO();
    }

    public CartEntryDAO getCartEntryDAO() {
        return daoSet.geCartEntryDAO();
    }

    public CartDAO getCartDAO() {
        return daoSet.geCartDAO();
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
