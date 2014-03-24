package cache;

import dao.iface.AddressDAO;
import domain.Address;

import java.util.List;

/**
 * Created by Iwan on 14.20.3.
 */
public class AddressCache implements AddressDAO {

    private AddressDAO addressDAO;

    public AddressCache(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    @Override
    public List<Address> getAddressesList() {
        return addressDAO.getAddressesList();
    }

    @Override
    public boolean exists(Address address) {
        return false;
    }

    @Override
    public Address getAddress(int id) {
        return addressDAO.getAddress(id);
    }

    @Override
    public Address getAddress(String name) {
        return addressDAO.getAddress(name);
    }

    @Override
    public void insertAddress(Address address) {
        addressDAO.insertAddress(address);
    }

    @Override
    public void updateAddress(Address address) {
        addressDAO.updateAddress(address);
    }

    @Override
    public void safeDeleteAddress(Address address) {
        addressDAO.safeDeleteAddress(address);
    }
}
