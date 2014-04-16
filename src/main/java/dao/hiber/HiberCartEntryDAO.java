package dao.hiber;

import dao.iface.CartEntryDAO;
import domain.Cart;
import domain.MultiCheese;
import org.hibernate.Query;
import org.hibernate.Session;
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
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(cheese);
        session.getTransaction().commit();
        session.close();
    }

    /*@Override
    public List<MultiCheese> getCartEntries(Cart cart) {
        return null;
    }*/

    //TODO пересмотреть, нужен ли метод
    @Override
    public List<MultiCheese> getCartEntries(int cartId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from MultiCheese where CartID = :id");
        query.setParameter("id", cartId);
        List list = query.list();
        session.getTransaction().commit();
        session.close();
        return list;
    }


}
