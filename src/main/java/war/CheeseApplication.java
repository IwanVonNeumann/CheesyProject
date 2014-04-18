package war;

import cache.iface.IDataCache;
import cache.mock.DataCacheMock;
import dao.hiber.HiberConnectionManager;
import dao.iface.ConnectionManager;
import dao.jdbc.JDBCConnectionManager;
import org.apache.wicket.*;
import org.apache.wicket.protocol.http.WebApplication;

public class CheeseApplication extends WebApplication {

    private IDataCache dataCache;

    public CheeseApplication() {
        ConnectionManager cm = new JDBCConnectionManager();
        //ConnectionManager cm = new HiberConnectionManager();
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
    public List<Cheese> getEntries() {
        return Collections.unmodifiableList(cheeses);
    } */
}
