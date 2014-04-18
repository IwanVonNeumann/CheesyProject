package dao.base;

import dao.iface.CartDAO;
import dao.iface.CartEntryDAO;
import dao.iface.CheeseDAO;
import dao.jdbc.dao.JDBCCartDAO;
import dao.jdbc.dao.JDBCCartEntryDAO;
import dao.jdbc.dao.JDBCCheeseDAO;
import domain.Cart;
import domain.CartEntry;
import domain.Cheese;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Iwan on 14.23.3
 */
public class CartEntryDAOTest extends DAOTest {

    private static CartDAO cartDAO;
    private static CheeseDAO cheeseDAO;
    private static CartEntryDAO cartEntryDAO;

    @Before
    public void initDAO() {
        entity = "cartentry";
        executeFile(entity, "createTables.sql");
        executeFile(entity, "insertData.sql");
        cartDAO = new JDBCCartDAO(connection);
        cheeseDAO = new JDBCCheeseDAO(connection);
        cartEntryDAO = new JDBCCartEntryDAO(connection);
    }

    @After
    public void cleanupData() {
        executeFile(entity, "dropTables.sql");
    }

    @Test
    public void gettingCartEntriesListByIdTest() {
        List<CartEntry> list1 = cartEntryDAO.getCartEntries(1);
        assertEquals(2, list1.size());

        List<CartEntry> list2 = cartEntryDAO.getCartEntries(2);
        assertEquals(1, list2.size());

        List<CartEntry> list3 = cartEntryDAO.getCartEntries(3);
        assertEquals(0, list3.size());


        Cart cart1 = cartDAO.getCartsList().get(0);
        List<CartEntry> list4 = cartEntryDAO.getCartEntries(cart1.getId());
        assertTrue(listsEqual(list4, cart1.getEntries()));


        List<CartEntry> list5 = cartEntryDAO.getCartEntries(0);
        assertEquals(0, list5.size());
    }

    @Test
    public void insertingTest() {
        Cart cart1 = cartDAO.getCartsList().get(0);
        Cheese cheese = cheeseDAO.getCheese(3);
        CartEntry cartEntry = new CartEntry(cheese, 5);

        assertFalse(itemInList(cart1.getEntries(), cartEntry));

        cartEntryDAO.insertCartEntry(cart1, cartEntry);
        Cart cart2 = cartDAO.getCartsList().get(0);

        assertTrue(itemInList(cart2.getEntries(), cartEntry));
    }
}