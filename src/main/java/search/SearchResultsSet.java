package search;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Iwan on 30.04.2014
 */
public class SearchResultsSet {

    private List<SearchResult> searchResults;

    public SearchResultsSet() {
        searchResults = new LinkedList<>();
    }

    public void add(SearchResult searchResult) {
        if (!presents(searchResult)) {
            searchResults.add(searchResult);
        }
    };

    public List<SearchResult> getSearchResults() {
        return searchResults;
    }

    public int size() {
        return searchResults.size();
    }

    public void sort() {
        Collections.sort(searchResults);
    }

    private boolean presents(SearchResult searchResult) {
        for (SearchResult current : searchResults) {
            if (current.equals(searchResult)) return true;
        }
        return false;
    }
}