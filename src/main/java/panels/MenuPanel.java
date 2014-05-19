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

            @Override
            public boolean isEnabled() {
                return viewIsNot(StoreView.class);
            }
        });

        add(new Link("search") {
            @Override
            public void onClick() {
                setResponsePage(new SearchView());
            }

            @Override
            public boolean isEnabled() {
                return viewIsNot(SearchView.class);
            }
        });

        add(new Link("cheeses") {
            @Override
            public void onClick() {
                setResponsePage(new CheesesView());
            }

            @Override
            public boolean isEnabled() {
                return viewIsNot(CheesesView.class);
            }
        });

        add(new Link("purchases") {
            @Override
            public void onClick() {
                setResponsePage(new PurchasesView());
            }

            @Override
            public boolean isEnabled() {
                return viewIsNot(PurchasesView.class);
            }
        });

        add(new Link("customers") {
            @Override
            public void onClick() {
                setResponsePage(new CustomersView());
            }

            @Override
            public boolean isEnabled() {
                return viewIsNot(CustomersView.class);
            }
        });

        add(new Link("profile") {
            @Override
            public void onClick() {
                setResponsePage(new ProfileView());
            }

            @Override
            public boolean isEnabled() {
                return viewIsNot(ProfileView.class);
            }
        });
    }

    private PageHeadPanel getPageHeadPanel() {
        return (PageHeadPanel) getParent();
    }

    private Class getViewClass() {
        return getPageHeadPanel().getView().getClass();
    }

    private boolean viewIsNot(Class viewClass) {
        return getViewClass() != viewClass;
    }
}