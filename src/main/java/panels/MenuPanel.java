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
                setResponsePage(StoreView.class);
            }
        });

        add(new Link("search") {
            @Override
            public void onClick() {
                setResponsePage(SearchView.class);
            }
        });

        add(new Link("cheeses") {
            @Override
            public void onClick() {
                setResponsePage(CheesesView.class);
            }
        });

        add(new Link("purchases") {
            @Override
            public void onClick() {
                setResponsePage(PurchasesView.class);
            }
        });

        add(new Link("customers") {
            @Override
            public void onClick() {
                setResponsePage(CustomersView.class);
            }
        });

        add(new Link("profile") {
            @Override
            public void onClick() {
                setResponsePage(ProfileView.class);
            }
        });
    }
}
