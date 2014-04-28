package search.proc;

import cache.iface.IDataCache;
import domain.Cheese;

import java.util.List;

/**
 * Created by Iwan on 28.04.2014
 */
public interface ISearchProcedure {

    List<Cheese> search(IDataCache dataCache, String key);
}
