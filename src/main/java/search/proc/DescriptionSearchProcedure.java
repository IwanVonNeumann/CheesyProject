package search.proc;

import cache.iface.IDataCache;
import domain.Cheese;

import java.util.List;

/**
 * Created by Iwan on 28.04.2014
 */
public class DescriptionSearchProcedure implements ISearchProcedure {

    @Override
    public List<Cheese> search(IDataCache dataCache, String key) {
        return dataCache.searchCheeseByDescription(key);
    }
}