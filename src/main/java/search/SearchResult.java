package search;

import domain.Cheese;

/**
 * Created by Iwan on 28.04.2014
 */
public class SearchResult {

    private Cheese cheese;
    private String key;

    public SearchResult(Cheese cheese, String key) {
        this.cheese = cheese;
        this.key = key;
    }

    @Override
    public String toString() {
        return "SearchResult {Cheese " + cheese.getName() +
                " found by key '" + key + "'";
    }
}