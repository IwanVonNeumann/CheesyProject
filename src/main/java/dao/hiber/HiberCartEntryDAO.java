package dao.hiber;

import dao.iface.CartEntryDAO;
import domain.Cart;
import domain.CartEntry;
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
    public void insertCartEntry(Cart cart, CartEntry cheese) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(cheese);
        session.getTransaction().commit();
        session.close();
    }

    /*@Override
    public List<CartEntry> getCartEntries(Cart cart) {
        return null;
    }*/

    //TODO пересмотреть, нужен ли метод
    @Override
    public List<CartEntry> getCartEntries(int cartId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from CartEntry where CartID = :id");
        query.setParameter("id", cartId);
        List list = query.list();
        session.getTransaction().commit();
        session.close();
        return list;
        /*Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Cart cart = (Cart)session
                .createCriteria(Cart.class)
                .add(Restrictions.eq("id", cartId))
                .uniqueResult();
        transaction.commit();
        session.close();
        return cart.getCheeses();*/
    }


}
