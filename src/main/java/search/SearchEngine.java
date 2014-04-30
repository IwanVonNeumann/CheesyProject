package search;

import cache.iface.IDataCache;
import domain.Cheese;
import org.apache.wicket.util.value.ValueMap;
import search.proc.DescriptionSearchProcedure;
import search.proc.ISearchProcedure;
import search.proc.NameSearchProcedure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Iwan on 28.04.2014
 */
public class SearchEngine {

    private static Map<String, ISearchProcedure> procedures = new HashMap<>();

    static {
        procedures.put("name", new NameSearchProcedure());
        procedures.put("description", new DescriptionSearchProcedure());
    }

    public static SearchResultsSet search(IDataCache dataCache, ValueMap pars) {
        System.out.println("Running Search Engine...");
        String key = (String)pars.get("query");

        System.out.println("Search key: " + key );

        List<String> criteria = (List<String>)pars.get("criteria");

        SearchResultsSet resultsSet = new SearchResultsSet();

        // no search pars defined
        if (criteria.size() == 0) {
            criteria.add("name");
            criteria.add("description");
        }

        // advanced search
        for(String criterion : criteria) {
            System.out.println("Searching by " + criterion + "...");
            List<Cheese> cheeses = procedures.get(criterion).search(dataCache, key);
            System.out.println(cheeses.size() + " items found;");
            for (Cheese cheese : cheeses) {
                resultsSet.add(new SearchResult(cheese, key));
            }
        }
        resultsSet.sort();

        return resultsSet;
    }
}