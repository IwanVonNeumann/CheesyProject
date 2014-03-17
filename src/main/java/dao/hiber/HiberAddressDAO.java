package dao.hiber;

import dao.iface.AddressDAO;
import domain.Address;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Iwan on 14.15.3.
 */
public class HiberAddressDAO extends HiberDAO implements AddressDAO {

    public HiberAddressDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Address> getAddressesList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery("from Address where deleted <> true").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public Address getAddress(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Address address = (Address)session.get(Address.class, id);
        session.getTransaction().commit();
        session.close();
        return address;
    }

    @Override
    public Address getAddress(String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Address where name = :name ");
        query.setParameter("name", name);
        List list = query.list();
        Address address = list != null ? (Address) list.get(0) : null;
        session.getTransaction().commit();
        session.close();
        return address;
    }

    @Override
    public void insertAddress(Address address) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(address);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateAddress(Address address) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(address);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void safeDeleteAddress(Address address) {
        address.delete();
        updateAddress(address);
    }
}