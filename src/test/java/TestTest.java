import search.SearchResult;
import search.StringUtils;

/**
 * Created by IRuskevich on 16.04.2014
 */
public class TestTest {

    public static void main(String[] args) {
        String string = "Aabaa";
        String substring = "aa";

        String markup = StringUtils.highlightAllKeys(string, substring);
        System.out.println(markup);
    }
}
