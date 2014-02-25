package panels;


import org.apache.wicket.markup.html.link.Link;
import views.ViewProfile;
import war.Index;
import views.ViewCheeses;
import views.ViewCustomers;
import views.ViewPurchases;

public class MenuPanel extends CheesePanel {

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

        add(new Link("profile") {
            @Override
            public void onClick() {
                setResponsePage(ViewProfile.class);
            }
        });
    }
}
