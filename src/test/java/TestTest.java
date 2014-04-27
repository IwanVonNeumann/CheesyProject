import dao.hiber.HiberConnectionManager;
import dao.iface.ConnectionManager;
import dao.iface.DBConnection;
import domain.Address;
import domain.Cart;
import domain.CartEntry;
import domain.Title;

import java.util.List;

/**
 * Created by IRuskevich on 16.04.2014
 */
public class TestTest {

    public static void main(String[] args) {

        Title title = Title.getConstant("Mr.");
        System.out.println(title);
     }
}
