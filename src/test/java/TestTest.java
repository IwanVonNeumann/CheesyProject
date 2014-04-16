import dao.hiber.HiberConnectionManager;
import dao.iface.ConnectionManager;
import dao.iface.DBConnection;
import domain.Cheese;
import domain.MultiCheese;

import java.util.List;

/**
 * Created by IRuskevich on 16.04.2014
 */
public class TestTest {

    public static void main(String[] args) {

        ConnectionManager connectionManager = new HiberConnectionManager();
        DBConnection connection = connectionManager.getConnection();

        List<MultiCheese> cheeses = connection.getCartEntryDAO().getCartEntries(2);

        for (MultiCheese cheese : cheeses) {
            System.out.println(cheese);
        }


    }
}
