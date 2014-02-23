package war;


import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class MenuPanel extends Panel {

    public MenuPanel(String id) {
        super(id);

        add(new Link("home") {
            @Override
            public void onClick() {
                setResponsePage(Index.class);
            }
        });

        add(new Link("cheeses") {
            @Override
            public void onClick() {
                setResponsePage(ViewCheeses.class);
            }
        });

        add(new Link("purchases") {
            @Override
            public void onClick() {
                setResponsePage(ViewPurchases.class);
            }
        });

        add(new Link("customers") {
            @Override
            public void onClick() {
                setResponsePage(ViewCustomers.class);
            }
        });

        // Profile/Logoff
        if (getCheeseSession().getAddress() == null) {
            setResponsePage(LoginPage.class);
        }

        String message = "Logged in as " +
                getCheeseSession().getAddress().getName();

        add(new Label("userID", message));

        add(new Link("logout") {
            @Override
            public void onClick() {
                getCheeseSession().logout();
                setResponsePage(LoginPage.class);
            }
        });

    }

    protected CheeseSession getCheeseSession() {
        return (CheeseSession) getSession();
    }
}
