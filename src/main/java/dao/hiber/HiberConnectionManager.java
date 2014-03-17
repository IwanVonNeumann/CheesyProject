package dao.hiber;

import dao.iface.ConnectionManager;
import dao.iface.DBConnection;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by Iwan on 14.15.3.
 */
public class HiberConnectionManager implements ConnectionManager {

    private SessionFactory sessionFactory;

    public HiberConnectionManager() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public DBConnection getConnection() {
        return new HiberConnection(sessionFactory);
    }

    public void shutDown() {
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }
}