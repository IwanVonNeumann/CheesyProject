package cache;

import dao.iface.AddressDAO;
import domain.Address;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Iwan on 14.20.3
 */
public class AddressCache implements AddressDAO {

    private AddressDAO addressDAO;

    private List<Address> addresses;
    private List<Address> deletedAddresses;

    public AddressCache(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
        addresses = new LinkedList<>(addressDAO.getAddressesList());
        deletedAddresses = new LinkedList<>();
    }

    @Override
    public List<Address> getAddressesList() {
        return addresses;
    }

    @Override
    public boolean exists(Address address) {
        for (Address current : addresses) {
            if (current.equals(address)) return true;
        }
        return false;
    }

    @Override
    public Address getAddress(int id) {
        for (Address address : addresses) {
            if (address.getId() == id) return address;
        }
        for (Address address : deletedAddresses) {
            if (address.getId() == id) return address;
        }
        return null;
    }

    @Override
    public Address getAddress(String name) {
        for (Address address : addresses) {
            if (address.getName().equals(name)) return address;
        }
        for (Address address : deletedAddresses) {
            if (address.getName().equals(name)) return address;
        }
        return null;
    }

    @Override
    public void insertAddress(Address address) {
        addresses.add(address);
        addressDAO.insertAddress(address);
    }

    @Override
    public void updateAddress(Address address) {
        addressDAO.updateAddress(address);
    }

    @Override
    public void safeDeleteAddress(Address address) {
        addresses.remove(address);
        address.delete();
        deletedAddresses.add(address);
        addressDAO.safeDeleteAddress(address);
    }
}
