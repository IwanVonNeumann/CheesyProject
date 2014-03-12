package dao.iface;

import domain.Address;

import java.util.List;

/**
 * Created by IRuskevich on 14.12.3.
 */
public interface AddressDAO {

    public List<Address> getAddressesList();

    public void insertAddress(Address address);

    public Address getAddress(int id);

    public void updateAddress(Address address);

}
