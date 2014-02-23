package war;

import org.apache.wicket.*;
import org.apache.wicket.protocol.http.WebApplication;

public class CheeseApplication extends WebApplication {

    //private List<Cheese> cheeses;

    public CheeseApplication() {
        //cheeses = getCheeses();
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
        return new CheeseSession(request);
    }

    /*
    public List<Cheese> getCheeses() {
        return Collections.unmodifiableList(cheeses);

    } */
}
