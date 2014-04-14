package dao.hiber;

import dao.iface.ConnectionManager;
import dao.iface.DBConnection;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by Iwan on 14.15.3
 */
public class HiberConnectionManager implements ConnectionManager {

    private SessionFactory prodSessionFactory;
    private SessionFactory testSessionFactory;

    public HiberConnectionManager() {
        Configuration prodConf = new Configuration();
        prodConf.configure("hibernate.cfg.xml");
        ServiceRegistry prodServiceRegistry = new StandardServiceRegistryBuilder().
                applySettings(prodConf.getProperties()).build();
        prodSessionFactory = prodConf.buildSessionFactory(prodServiceRegistry);

        Configuration testConf = new Configuration();
        testConf.configure("hibernate_test.cfg.xml");
        ServiceRegistry testServiceRegistry = new StandardServiceRegistryBuilder().
                applySettings(testConf.getProperties()).build();
        testSessionFactory = testConf.buildSessionFactory(testServiceRegistry);
    }

    @Override
    public DBConnection getConnection() {
        return new HiberConnection(prodSessionFactory);
    }

    @Override
    public DBConnection getTestConnection() {
        return new HiberConnection(testSessionFactory);
    }

    public void shutDown() {
        if ( prodSessionFactory != null ) {
            prodSessionFactory.close();
        }
        if (testSessionFactory != null) {
            testSessionFactory.close();
        }
    }
}