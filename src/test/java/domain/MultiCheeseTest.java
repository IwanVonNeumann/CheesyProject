package domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by IRuskevich on 25.03.2014
 */
public class MultiCheeseTest {

    @Test
    public void equalityTest() {
        Cheese cheese1 = new Cheese(1, "Cheese1", "Desc1", 1.00);

        MultiCheese multiCheese1 = new MultiCheese(cheese1, 1);
        MultiCheese multiCheese2 = multiCheese1;

        assertEquals(multiCheese1, multiCheese2);

        MultiCheese multiCheese3 = new MultiCheese(cheese1, 1);
        MultiCheese multiCheese4 = new MultiCheese(cheese1, 2);

        assertEquals(multiCheese1, multiCheese3);
        assertNotEquals(multiCheese1, multiCheese4);

        Cheese cheese2 = new Cheese(1, "Cheese1", "Desc1", 1.00);

        MultiCheese multiCheese5 = new MultiCheese(cheese2, 1);

        assertEquals(multiCheese1, multiCheese5);
    }
}
