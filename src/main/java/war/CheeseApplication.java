package war;

import cache.DataCache;
import dao.iface.ConnectionManager;
import dao.jdbc.JDBCConnectionManager;
import org.apache.wicket.*;
import org.apache.wicket.protocol.http.WebApplication;

public class CheeseApplication extends WebApplication {

    //private List<Cheese> cheeses;

    private ConnectionManager cm;
    private DataCache dataCache;

    public CheeseApplication() {
        //cheeses = getCheeses();
        cm = new JDBCConnectionManager();
        dataCache = new DataCache();
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
        return new CheeseSession(request, cm.getConnection());
    }

    /*
    public List<Cheese> getCheeses() {
        return Collections.unmodifiableList(cheeses);

    } */
}
