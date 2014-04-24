package war;

import cache.iface.IDataCache;
import cache.mock.DataCacheMock;
import dao.iface.ConnectionManager;
import org.apache.wicket.*;
import org.apache.wicket.protocol.http.WebApplication;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CheeseApplication extends WebApplication {

    private IDataCache dataCache;

    public CheeseApplication() {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring.cfg.xml");

        ConnectionManager connectionManager = (ConnectionManager)context.getBean("connectionManager");

        dataCache = new DataCacheMock(connectionManager.getConnection());
    }

    @Override
    protected void init() {}

    public static CheeseApplication get() {
        return (CheeseApplication) Application.get();
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return LoginPage.class;
    }

    @Override
    public Session newSession(Request request, Response response) {
        return new CheeseSession(request, dataCache);
    }

    /*
    public List<Cheese> getEntries() {
        return Collections.unmodifiableList(cheeses);
    } */
}