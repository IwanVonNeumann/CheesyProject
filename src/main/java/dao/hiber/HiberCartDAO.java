package dao.hiber;

import dao.iface.CartDAO;
import domain.Address;
import domain.Cart;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Iwan on 14.15.3.
 */
public class HiberCartDAO extends HiberDAO implements CartDAO {

    public HiberCartDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Cart> getCartsList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery("from Cart").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public List<Cart> getCartsList(Address address) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Carts where CustomerID = :id ");
        query.setParameter("id", address.getId());
        List result = query.list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void insertCart(Cart cart) {
        /*cart.setCustomerID(address.getId());
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(cart);
        session.getTransaction().commit();
        session.close();*/
    }
}