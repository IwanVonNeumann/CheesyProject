import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by IRuskevich on 26.03.2014
 */
public class metaTest {

    @Test
    public void test1() {
        List<Integer> list1 = new LinkedList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        printList(list1);

        List<Integer> list2 = new LinkedList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        printList(list2);

        assertTrue(listsEqual(list1, list2));

        list2.remove(1);
        printList(list2);

        assertFalse(listsEqual(list1, list2));

    }

    private <T> void printList(Collection<T> list) {
        for (T item : list) {
            System.out.println(item);
        }
    }

    protected <T> boolean listsEqual(List<T> list1, List<T> list2) {
        if (list1.size() != list2.size()) return false;
        for (T item : list1) {
            if (!itemInList(list2, item)) return false;
        }
        return true;
    }

    protected <T> boolean itemInList(List<T> list, T item) {
        for (T current : list) {
            if (current.equals(item)) return true;
        }
        return false;
    }
}
