package dao.iface;

import domain.Address;
import domain.Cheese;
import domain.Title;

/**
 * Created by Iwan on 12.10.2014
 */

public interface ProxyFactory {

    Address getAddressProxy(Title title, String name, String street, String city,
                            Integer zipCode, int id, byte[] hash, boolean deleted);

    Cheese getCheeseProxy(int id, String name, String description, Double price,
                          boolean deleted);
}
