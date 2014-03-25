package domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by IRuskevich on 25.03.2014
 */
public class CheeseTest {

    @Test
    public void equalityTest() {
        Cheese cheese1 = new Cheese(1, "Cheese1", "Desc1", 1.00);
        Cheese cheese2 = cheese1;

        assertEquals(cheese1, cheese2);

        Cheese cheese3 = new Cheese(1, "Cheese1", "Desc1", 1.00);

        assertEquals(cheese1, cheese3);
    }
}
