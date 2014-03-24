package dao.iface;

/**
 * Created by IRuskevich on 14.14.3
 */
public interface DBConnection {

    AddressDAO getAddressDAO();

    CartDAO getCartDAO();

    CartEntryDAO getCartEntryDAO();

    CheeseDAO getCheeseDAO();
}