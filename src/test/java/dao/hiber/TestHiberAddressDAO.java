package dao.hiber;

import dao.iface.AddressDAO;
import dao.iface.ConnectionManager;
import dao.iface.DBConnection;
import domain.Address;

/**
 * Created by Iwan on 14.15.3.
 */
public class TestHiberAddressDAO {

    public static void main(String[] args) {
        ConnectionManager cm = new HiberConnectionManager();
        DBConnection hc = cm.getConnection();
        AddressDAO dao = hc.getAddressDAO();

        Address address = dao.getAddress(7);
        System.out.println(address);

        address = dao.getAddress(1);
        System.out.println(address);

        address = dao.getAddress("Iwan");
        System.out.println(address);
    }
}