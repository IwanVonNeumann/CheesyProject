package search;

import cache.iface.IDataCache;
import domain.Cheese;
import org.apache.wicket.util.value.ValueMap;
import search.proc.DescriptionSearchProcedure;
import search.proc.ISearchProcedure;
import search.proc.NameSearchProcedure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Iwan on 28.04.2014
 */
public class SearchEngine {

    private static Map<String, ISearchProcedure> procedures = new HashMap<>();

    static {
        procedures.put("Name", new NameSearchProcedure());
        procedures.put("Description", new DescriptionSearchProcedure());
    }

    public static List<SearchResult> search(IDataCache dataCache, ValueMap pars) {
        System.out.println("Running Search Engine...");
        String key = (String)pars.get("query");

        System.out.println("Search key: " + key );

        List<String> criteria = (List<String>)pars.get("criteria");

        List<SearchResult> resultList = new LinkedList<>();

        for(String criterion : criteria) {
            List<Cheese> cheeses = procedures.get(criterion).search(dataCache, key);
            for (Cheese cheese : cheeses) {
                resultList.add(new SearchResult(cheese, key));
            }
        }

        return resultList;
    }
}