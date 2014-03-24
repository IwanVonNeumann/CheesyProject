package dao;

import dao.iface.AddressDAO;
import dao.jdbc.dao.JDBCAddressDAO;

import domain.Address;

import domain.Cheese;
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
public class AddressDAOTest extends DAOTest {
    private static AddressDAO dao;

    @Before
    public void initDAO() {
        entity = "address";
        executeFile(entity, "createTables.sql");
        executeFile(entity, "insertData.sql");
        dao = new JDBCAddressDAO(connection);
    }

    @After
    public void cleanupData() {
        executeFile(entity, "dropTables.sql");
    }

    @Test
    public void gettingListTest() {
        List<Address> addresses = dao.getAddressesList();
        assertEquals(2, addresses.size());
    }

    @Test
    public void existenceTest() {
        Address address1 = new Address("John Wayne", "Penny Lane", "New York", 1123);
        assertTrue(dao.exists(address1));

        List<Address> addresses = dao.getAddressesList();
        Address address2 = addresses.get(0);
        assertTrue(dao.exists(address2));

        Address address3 = new Address("Iwan", "Muzyczna", "Lublin", 2102);
        assertFalse(dao.exists(address3));
    }

    @Test
    public void gettingByIdTest() {
        List<Address> addresses = dao.getAddressesList();
        Address address1 = addresses.get(0);
        Address address2 = dao.getAddress(address1.getId());
        assertEquals(address1, address2);

        Address address3 = dao.getAddress(0);
        assertEquals(address3, null);
    }

    @Test
    public void gettingByNameTest() {
        List<Address> addresses = dao.getAddressesList();
        Address address1 = addresses.get(0);
        Address address2 = dao.getAddress(address1.getName());
        assertEquals(address1, address2);

        Address address3 = dao.getAddress("Iwan");
        assertEquals(address3, null);
    }

    @Test
    public void insertingTest() {
        Address address1 = new Address("Iwan", "Muzyczna", "Lublin", 2102);
        assertFalse(dao.exists(address1));

        dao.insertAddress(address1);
        assertTrue(dao.exists(address1));

        List<Address> list1 = dao.getAddressesList();
        dao.insertAddress(address1);
        List<Address> list2 = dao.getAddressesList();

        assertEquals(list1.size(), list2.size());
    }

    @Test
    public void updatingTest() {
        Address address1 = dao.getAddress(1);
        String name = "new name";
        address1.setName(name);
        dao.updateAddress(address1);

        Address address2 = dao.getAddress(1);
        assertEquals(name, address2.getName());
    }

    @Test
    public void deletingTest() {
        List<Address> list1 = dao.getAddressesList();
        Address address1 = list1.get(1);
        dao.safeDeleteAddress(address1);
        assertFalse(dao.exists(address1));

        Address address2 = dao.getAddress(address1.getId());
        assertTrue(address2.isDeleted());
    }
}
