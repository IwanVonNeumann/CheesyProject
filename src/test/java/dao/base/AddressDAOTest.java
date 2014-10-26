package dao.base;

import dao.iface.AddressDAO;

import domain.Address;

import domain.Title;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Iwan on 14.22.3
 */

public abstract class AddressDAOTest extends DAOTest {

    protected static AddressDAO dao;

    @BeforeClass
    public static void setEntity() {
        entity = "address";
    }

    @Before
    public abstract void initDAO();

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
        Address address1 = new Address(Title.MR, "John Wayne", "Penny Lane", "New York", 1123);
        assertTrue(dao.exists(address1));

        List<Address> addresses = dao.getAddressesList();
        Address address2 = addresses.get(0);
        assertTrue(dao.exists(address2));

        Address address3 = new Address(Title.MR, "Iwan", "Muzyczna", "Lublin", 2102);
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
        Address address1 = new Address(Title.MR, "Iwan", "Muzyczna", "Lublin", 2102);
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
