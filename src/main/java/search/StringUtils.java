package search;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Iwan on 30.04.2014
 */
public class StringUtils {

    public static int countSubstrings(String string, String substring) {
        return getOccurrences(string, substring).length;
    }

    public static String highlightAllKeys(String string, String key) {
        int[] occurrences = getOccurrences(string, key);

        StringBuilder stringBuilder = new StringBuilder(string);
        for (int i = occurrences.length - 1; i >= 0; i--) {
            stringBuilder = highlightKey(stringBuilder, key, occurrences[i]);
        }

        return stringBuilder.toString();
    }

    private static StringBuilder highlightKey(StringBuilder stringBuilder,
                                             String key, int position) {
        final String open = "<b>";
        final String close = "</b>";
        stringBuilder.insert(position, open);
        stringBuilder.insert(position + open.length() + key.length(), close);
        return stringBuilder;
    }

    private static int[] getOccurrences(String string, String substring) {
        string = string.toUpperCase();
        substring = substring.toUpperCase();

        List<Integer> occurrences = new LinkedList<>();
        for (int i = -1; (i = string.indexOf(substring, i + 1)) != -1; ) {
            occurrences.add(i);
        }

        int[] results = new int[occurrences.size()];
        for (int i = 0; i < occurrences.size() ; i++) {
            results[i] = occurrences.get(i);
        }

        return results;
    }
}