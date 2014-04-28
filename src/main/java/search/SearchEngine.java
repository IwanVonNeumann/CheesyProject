package search;

import org.apache.wicket.util.value.ValueMap;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Iwan on 28.04.2014
 */
public class SearchEngine {

    public static List<SearchResult> search(String key) {
        System.out.println("Running Search Engine...");
        return new LinkedList<>();
    }

    public static List<SearchResult> search(String key, ValueMap criteria) {
        System.out.println("Running Search Engine with advanced options...");
        return new LinkedList<>();
    }
}