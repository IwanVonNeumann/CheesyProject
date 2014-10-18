package search;

import domain.Cheese;

import static search.StringUtils.countSubstrings;
import static search.StringUtils.highlightAllKeys;

/**
 * Created by Iwan on 28.04.2014
 */

public class SearchResult implements Comparable<SearchResult> {

    private Cheese cheese;
    private String key;

    private final int rank;

    private final static int NAME_WEIGHT = 10;
    private final static int DESC_WEIGHT = 1;

    public SearchResult(Cheese cheese, String key) {
        this.cheese = cheese;
        this.key = key;
        rank = calculateRank();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchResult)) return false;

        SearchResult that = (SearchResult) o;

        return cheese.equals(that.cheese);
    }

    @Override
    public String toString() {
        return "SearchResult {Cheese " + cheese.getName() +
                " found by key '" + key + "'}";
    }

    public String getFormattedName() {
        return highlightAllKeys(cheese.getName(), key);
    }

    public String getFormattedDescription() {
        return highlightAllKeys(cheese.getDescription(), key);
    }

    public Cheese getCheese() {
        return cheese;
    }

    @Override
    public int compareTo(SearchResult searchResult) {
        return searchResult.rank - rank;
    }

    private int calculateRank() {

        return countSubstrings(cheese.getName(), key) * NAME_WEIGHT +
                countSubstrings(cheese.getDescription(), key) * DESC_WEIGHT;
    }
}