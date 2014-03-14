package dao.iface;

import domain.Address;

import java.util.List;

/**
 * Created by IRuskevich on 14.12.3.
 */
public interface AddressDAO {

    List<Address> getAddressesList();

    Address getAddress(int id);

    Address getAddress(String name);

    void insertAddress(Address address);

    void updateAddress(Address address);

    // void deleteAddress(Address address); // удалить со временем

    void safeDeleteAddress(Address address);

}
