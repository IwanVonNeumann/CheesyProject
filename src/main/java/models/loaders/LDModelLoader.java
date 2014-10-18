package models.loaders;

import cache.iface.IDataCache;
import models.AddressesLDModel;
import models.CartEntriesLDModel;
import models.CartsLDModel;
import models.CheesesLDModel;
import org.apache.wicket.model.IModel;

/**
 * Created by Iwan on 12.10.2014
 */

public class LDModelLoader implements ModelLoader{

    private IDataCache dataCache;

    public LDModelLoader() {
    }

    public void setDataCache(IDataCache dataCache) {
        this.dataCache = dataCache;
    }

    @Override
    public IModel getAddressModel() {
        return new AddressesLDModel(dataCache);
    }

    @Override
    public IModel getCartEntriesModel() {
        return new CartEntriesLDModel(dataCache);
    }

    @Override
    public IModel getCartsModel() {
        return new CartsLDModel(dataCache);
    }

    @Override
    public IModel getCheesesModel() {
        return new CheesesLDModel(dataCache);
    }
}
