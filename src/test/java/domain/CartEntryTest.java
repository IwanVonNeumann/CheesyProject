package domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by IRuskevich on 25.03.2014
 */
public class CartEntryTest {

    @Test
    public void equalityTest() {
        Cheese cheese1 = new Cheese(1, "Cheese1", "Desc1", 1.00);

        CartEntry cartEntry1 = new CartEntry(cheese1, 1);
        CartEntry cartEntry2 = cartEntry1;

        assertEquals(cartEntry1, cartEntry2);

        CartEntry cartEntry3 = new CartEntry(cheese1, 1);
        CartEntry cartEntry4 = new CartEntry(cheese1, 2);

        assertEquals(cartEntry1, cartEntry3);
        assertNotEquals(cartEntry1, cartEntry4);

        Cheese cheese2 = new Cheese(1, "Cheese1", "Desc1", 1.00);

        CartEntry cartEntry5 = new CartEntry(cheese2, 1);

        assertEquals(cartEntry1, cartEntry5);
    }
}
