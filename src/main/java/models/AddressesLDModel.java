package models;

import cache.iface.IDataCache;
import domain.Address;

import java.util.List;

public class AddressesLDModel extends AbstractLDModel{

    public AddressesLDModel(IDataCache dataCache) {
        super(dataCache);
    }

    @Override
    protected Object load() {
        return getAddresses();
    }

    protected List<Address> getAddresses() {
        return dataCache.getAddressesList();
    }
}
