package war;

import cache.iface.IDataCache;
import cache.mock.DataCacheMock;

import dao.iface.ConnectionManager;
import dao.jdbc.JDBCConnectionManager;

import org.apache.wicket.*;
import org.apache.wicket.protocol.http.WebApplication;

public class CheeseApplication extends WebApplication {

    private IDataCache dataCache;

    public CheeseApplication() {
        ConnectionManager cm = new JDBCConnectionManager();
        dataCache = new DataCacheMock(cm.getConnection());
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
    public List<Cheese> getCheeses() {
        return Collections.unmodifiableList(cheeses);
    } */
}
