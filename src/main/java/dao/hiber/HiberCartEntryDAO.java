package dao.hiber;

import dao.iface.CartEntryDAO;
import domain.Cart;
import domain.MultiCheese;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Iwan on 14.15.3
 */
public class HiberCartEntryDAO extends HiberDAO implements CartEntryDAO {

    public HiberCartEntryDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void insertCartEntry(Cart cart, MultiCheese cheese) {

    }

    /*@Override
    public List<MultiCheese> getCartEntries(Cart cart) {
        return null;
    }*/

    @Override
    public List<MultiCheese> getCartEntries(int cartId) {
        return null;
    }


}
