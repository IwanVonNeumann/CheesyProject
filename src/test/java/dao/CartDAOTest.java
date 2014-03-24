package dao;

import dao.iface.AddressDAO;
import dao.iface.CartDAO;
import dao.iface.CartEntryDAO;
import dao.iface.CheeseDAO;
import dao.jdbc.dao.JDBCAddressDAO;
import dao.jdbc.dao.JDBCCartDAO;

import dao.jdbc.dao.JDBCCartEntryDAO;
import dao.jdbc.dao.JDBCCheeseDAO;
import domain.Address;
import domain.Cart;

import domain.MultiCheese;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Iwan on 14.22.3
 */
public class CartDAOTest extends DAOTest {

    private static CartDAO cartDAO;
    private static AddressDAO addressDAO;
    private static CheeseDAO cheeseDAO;
    private static CartEntryDAO cartEntryDAO;

    @Before
    public void initDAO() {
        entity = "cart";
        executeFile(entity, "createTables.sql");
        executeFile(entity, "insertData.sql");
        cartDAO = new JDBCCartDAO(connection);
        addressDAO = new JDBCAddressDAO(connection);
        cheeseDAO = new JDBCCheeseDAO(connection);
        cartEntryDAO = new JDBCCartEntryDAO(connection);
    }

    @After
    public void cleanupData() {
        executeFile(entity, "dropTables.sql");
    }

    @Test
    public void gettingListTest() {
        List<Cart> carts = cartDAO.getCartsList();
        assertEquals(3, carts.size());
    }

    @Test
    public void gettingListForCustomerTest() {
        Address address1 = addressDAO.getAddress("John Wayne");
        List<Cart> carts1 = cartDAO.getCartsList(address1);
        assertEquals(2, carts1.size());

        for (Cart c : carts1) {
            assertEquals(address1.getId(), c.getAddress().getId());
        }

        Address address2 = addressDAO.getAddress("John Newman");
        List<Cart> carts2 = cartDAO.getCartsList(address2);
        assertEquals(0, carts2.size());

        Address address3 = new Address("Iwan", "Muzyczna", "Lublin", 2102);
        List<Cart> carts3 = cartDAO.getCartsList(address3);
        assertEquals(0, carts3.size());
    }

    @Test
    public void insertingTest() {
        Cart cart1 = new Cart();
        cart1.setAddress(addressDAO.getAddress(3));
        cart1.order(); // текущее время

        List<Cart> list1 = cartDAO.getCartsList();
        assertFalse(cartInList(list1, cart1));

        cartDAO.insertCart(cart1);
        List<Cart> list2 = cartDAO.getCartsList();
        assertTrue(cartInList(list2, cart1));

        Cart cart2 = new Cart();
        cart2.setAddress(addressDAO.getAddress(2));
        cart2.addCheese(cheeseDAO.getCheese(1));
        cart2.addCheese(cheeseDAO.getCheese(2));
        cart2.order();

        List<Cart> list3 = cartDAO.getCartsList();
        assertFalse(cartInList(list3, cart2));

        cartDAO.insertCart(cart2);
        List<Cart> list4 = cartDAO.getCartsList();
        assertTrue(cartInList(list4, cart2));

        List<MultiCheese> items = cartEntryDAO.getCartEntries(cart2.getId());
        assertEquals(cart2.getCheeses().size(), items.size());

        assertTrue(items.retainAll(cart2.getCheeses()));
        assertTrue(cart2.getCheeses().retainAll(items));
    }

    private boolean cartInList(List<Cart> list, Cart cart) {
        for (Cart current : list) {
            if (current.equals(cart)) return true;
        }
        return false;
    }
}
