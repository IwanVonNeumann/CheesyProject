package models;

import dao.iface.AddressDAO;
import domain.Address;
import org.apache.wicket.model.LoadableDetachableModel;

import java.util.List;

public class AddressesLDModel extends LoadableDetachableModel{
    private AddressDAO dao;

    public AddressesLDModel(AddressDAO dao) {
        super();
        this.dao = dao;
    }

    @Override
    protected Object load() {
        return getAddresses();
    }

    protected List<Address> getAddresses() {
        return dao.getAddressesList();
    }
}
