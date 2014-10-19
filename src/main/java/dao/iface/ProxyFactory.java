package dao.iface;

import domain.Address;
import domain.Cheese;

import java.util.List;

/**
 * Created by Iwan on 12.10.2014
 */

public interface ProxyFactory {

    Address getAddressProxy(Address address);

    List<Address> getAddressProxyList(List<Address> addressList);

    Cheese getCheeseProxy(Cheese cheese);

    List<Cheese> getCheeseProxyList(List<Cheese> cheesesList);
}
