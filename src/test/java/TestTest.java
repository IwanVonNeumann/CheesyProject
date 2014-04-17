import dao.hiber.HiberConnectionManager;
import dao.iface.ConnectionManager;
import dao.iface.DBConnection;
import domain.CartEntry;

import java.util.List;

/**
 * Created by IRuskevich on 16.04.2014
 */
public class TestTest {

    public static void main(String[] args) {

        ConnectionManager connectionManager = new HiberConnectionManager();
        DBConnection connection = connectionManager.getConnection();

        List<CartEntry> entries = connection.getCartEntryDAO().getCartEntries(2);

        for (CartEntry entry : entries) {
            System.out.println(entry);
        }


    }
}
