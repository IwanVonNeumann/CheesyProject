package dao.hiber;

import dao.iface.CheeseDAO;
import domain.Cheese;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Iwan on 14.15.3
 */
public class HiberCheeseDAO extends HiberDAO implements CheeseDAO {

    public HiberCheeseDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Cheese> getCheesesList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery("from Cheese where deleted <> true").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public Cheese getCheese(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Cheese cheese = (Cheese) session.get(Cheese.class, id);
        session.getTransaction().commit();
        session.close();
        return cheese;
    }

    // TODO refactor
    // http://stackoverflow.com/questions/18558618/hibernate-insert-if-record-does-not-exist
    @Override
    public void addCheese(Cheese cheese) {
        if (!exists(cheese)) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(cheese);
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public void updateCheese(Cheese cheese) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(cheese);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void safeDeleteCheese(Cheese cheese) {
        cheese.delete();
        updateCheese(cheese);
    }

    @Override
    public boolean exists(Cheese cheese) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Cheese where name = :name and deleted <> true");
        query.setParameter("name", cheese.getName());
        List list = query.list();
        session.getTransaction().commit();
        session.close();
        return (list.size() > 0);
    }
}
