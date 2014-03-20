package cache.iface;

import dao.iface.AddressDAO;
import dao.iface.CartDAO;
import dao.iface.CartEntryDAO;
import dao.iface.CheeseDAO;

/**
 * Created by IRuskevich on 20.03.2014.
 */

public interface IDataCache extends AddressDAO, CartDAO, CartEntryDAO, CheeseDAO {

}
