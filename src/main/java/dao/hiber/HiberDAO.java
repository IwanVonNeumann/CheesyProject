package dao.hiber;

import org.hibernate.SessionFactory;

/**
 * Created by Iwan on 14.15.3
 */
public abstract class HiberDAO {

    protected SessionFactory sessionFactory;

    protected HiberDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
