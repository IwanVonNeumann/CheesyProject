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
        return highlightKey(cheese.getName(), key);
    }

    public String getFormattedDescription() {
        return highlightKey(cheese.getDescription(), key);
    }

    private String highlightKey(String string, String key) {
        StringBuilder stringBuilder = new StringBuilder();

        String[] elements = string.split(key);
        int k = elements.length - 1;

        for (int i = 0; i < k; i++) {
            stringBuilder.append(elements[i]);
            stringBuilder = appendKey(stringBuilder, key);
        }
        stringBuilder.append(elements[k]);

        int checkSum = 0;
        for (String element : elements) {
            checkSum += element.length();
        }
        checkSum += key.length() * k;

        if (checkSum < string.length()) {
            stringBuilder = appendKey(stringBuilder, key);
        }

        return stringBuilder.toString();
    }

    private StringBuilder appendKey(StringBuilder stringBuilder, String key) {
        stringBuilder.append("<b>")
                .append(key)
                .append("</b>");
        return stringBuilder;
    }
}