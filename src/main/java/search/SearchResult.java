package search;

/**
 * Created by Iwan on 28.04.2014
 */
public class SearchResult {

    private String name;
    private String description;

    public SearchResult(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}