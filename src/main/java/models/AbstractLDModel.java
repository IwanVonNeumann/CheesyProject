package models;

import cache.iface.IDataCache;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 * Created by Iwan on 12.10.2014
 */

public abstract class AbstractLDModel extends LoadableDetachableModel {

    protected IDataCache dataCache;

    protected AbstractLDModel(IDataCache dataCache) {
        this.dataCache = dataCache;
    }
}
