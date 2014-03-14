package models;

import dao.iface.AddressDAO;
import domain.Address;
import org.apache.wicket.model.LoadableDetachableModel;

import java.util.List;

public class CustomersModel extends LoadableDetachableModel{
    private AddressDAO dao;

    public CustomersModel(AddressDAO dao) {
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
