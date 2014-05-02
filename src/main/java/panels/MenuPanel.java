package panels;


import org.apache.wicket.markup.html.link.Link;
import views.*;
import views.StoreView;

public class MenuPanel extends CheesePanel {

    public MenuPanel(String id) {
        super(id);

        add(new Link("store") {
            @Override
            public void onClick() {
                setResponsePage(new StoreView());
            }
        });

        add(new Link("search") {
            @Override
            public void onClick() {
                setResponsePage(new SearchView());
            }
        });

        add(new Link("cheeses") {
            @Override
            public void onClick() {
                setResponsePage(new CheesesView());
            }
        });

        add(new Link("purchases") {
            @Override
            public void onClick() {
                setResponsePage(new PurchasesView());
            }
        });

        add(new Link("customers") {
            @Override
            public void onClick() {
                setResponsePage(new CustomersView());
            }
        });

        add(new Link("profile") {
            @Override
            public void onClick() {
                setResponsePage(new ProfileView());
            }
        });
    }
}
