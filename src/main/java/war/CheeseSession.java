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

import cache.iface.IDataCache;
import domain.Address;
import domain.Cart;
import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

public class CheeseSession extends WebSession {

    private Cart cart;
    private Address address;

    private IDataCache dataCache;

    protected CheeseSession(Request request, IDataCache dataCache) {
        super(request);
        cart = new Cart();
        this.dataCache = dataCache;
    }

    public Cart getCart() {
        return cart;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public IDataCache getDataCache() {
        return  dataCache;
    }

    public void logout() {
        invalidate();
        //address = null;
        //cart = new Cart();
    }
}
