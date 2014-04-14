package dao.base;

import dao.iface.CheeseDAO;

import domain.Cheese;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by IRuskevich on 20.03.2014
 */

public abstract class CheeseDAOTest extends DAOTest {

    protected static CheeseDAO dao;

    @BeforeClass
    public static void setEntity() {
        entity = "cheese";
    }

    @Before
    public abstract void initDAO();

    @After
    public void cleanupData() {
        executeFile(entity, "dropTables.sql");
    }

    @Test
    public void gettingListTest() {
        List<Cheese> cheeses = dao.getCheesesList();

        for (Cheese cheese : cheeses) {
            System.out.println(cheese);
        }

        assertEquals(3, cheeses.size());
    }

    @Test
    public void existenceTest() {
        Cheese cheese1 = new Cheese(0,
                "Gouda", "Gouda is a yellowish Dutch...", 1.65);
        assertTrue(dao.exists(cheese1));

        List<Cheese> cheeses = dao.getCheesesList();
        Cheese cheese2 = cheeses.get(0);
        assertTrue(dao.exists(cheese2));

        Cheese cheese3 = new Cheese(0,
                "Buxton Blue", "Buxton Blue cheese...", 0.99);
        assertFalse(dao.exists(cheese3));
    }

    @Test
    public void gettingByIDTest() {
        List<Cheese> cheeses = dao.getCheesesList();
        Cheese cheese1 = cheeses.get(0);
        Cheese cheese2 = dao.getCheese(cheese1.getId());
        assertEquals(cheese1, cheese2);

        Cheese cheese3 = dao.getCheese(0);
        assertEquals(cheese3, null);
    }

    @Test
    public void addingTest() {
        Cheese cheese = new Cheese(
                0, "Brie", "Brie is a soft cows' milk cheese...", 3.15);
        assertFalse(dao.exists(cheese));

        dao.addCheese(cheese);
        assertTrue(dao.exists(cheese));

        List<Cheese> list1 = dao.getCheesesList();
        dao.addCheese(cheese);
        List<Cheese> list2 = dao.getCheesesList();

        assertEquals(list1.size(), list2.size());
    }

    @Test
    public void updatingTest() {
        Cheese cheese1 = dao.getCheese(1);
        String name = "new name";
        cheese1.setName(name);
        dao.updateCheese(cheese1);

        Cheese cheese2 = dao.getCheese(1);
        assertEquals(name, cheese2.getName());
    }

    @Test
    public void deletingTest() {
        List<Cheese> list1 = dao.getCheesesList();
        Cheese cheese1 = list1.get(1);
        dao.safeDeleteCheese(cheese1);
        assertFalse(dao.exists(cheese1));

        Cheese cheese2 = dao.getCheese(cheese1.getId());
        assertTrue(cheese2.isDeleted());
    }
}
