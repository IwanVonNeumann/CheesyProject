package dao.iface;

import domain.Address;

import java.util.List;

/**
 * Created by IRuskevich on 14.12.3
 */
public interface AddressDAO {

    List<Address> getAddressesList();

    boolean exists(Address address);

    Address getAddress(long id);

    Address getAddress(String name);

    void insertAddress(Address address);

    void updateAddress(Address address);

    void safeDeleteAddress(Address address);

}
