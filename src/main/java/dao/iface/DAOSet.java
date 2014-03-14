package dao.iface;

/**
 * Created by IRuskevich on 14.14.3.
 */
public interface DAOSet {

    AddressDAO getAddressDAO();

    CartDAO geCartDAO();

    CartEntryDAO geCartEntryDAO();

    CheeseDAO geCheeseDAO();
}