package models;

import dao.jdbc.JDBCAddressDAO;
import domain.Address;
import org.apache.wicket.model.LoadableDetachableModel;

import java.util.List;

public class CustomersModel extends LoadableDetachableModel{
    private JDBCAddressDAO dao;

    public CustomersModel(JDBCAddressDAO dao) {
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
