package dao;

import dao.iface.CheeseDAO;
import dao.jdbc.dao.JDBCCheeseDAO;
import domain.Cheese;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by IRuskevich on 20.03.2014
 */
public class CheeseDAOTest extends DAOTest {

    private static CheeseDAO dao;

    public static void initDAO() {
        dao = new JDBCCheeseDAO(connection);
    }

    @Test
    public void getCheesesTest() {
        List<Cheese> cheeses = dao.getCheesesList();
        assertEquals(cheeses.size(), 12);
    }

    @Test
    public void getCheeseByIDTest() {
        List<Cheese> cheeses = dao.getCheesesList();
        Cheese cheese1 = cheeses.get(0);
        Cheese cheese2 = dao.getCheese(cheese1.getId());
        assertEquals(cheese1, cheese2);
    }

}
