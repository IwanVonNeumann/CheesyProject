package panels.component;

import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import panels.CheesePanel;
import views.CheesesView;
import views.CustomersView;
import views.ProfileView;
import views.PurchasesView;
import views.SearchView;
import views.StoreView;
import war.LoginPage;

import java.util.HashMap;
import java.util.Map;

public class PageHeadPanel extends CheesePanel {

    private Map<String, WebMarkupContainer> linksMap = new HashMap<>();

    public PageHeadPanel(String id) {
        super(id);

        // Profile/Logoff
        if (getCheeseSession().getAddress() == null) {
            System.out.println("Going to login...");
            throw new RestartResponseException(LoginPage.class);
        }

        add(createAndSaveLink("store", StoreView.class));
        add(createAndSaveLink("search", SearchView.class));
        add(createAndSaveLink("cheeses", CheesesView.class));
        add(createAndSaveLink("purchases", PurchasesView.class));
        add(createAndSaveLink("customers", CustomersView.class));
        add(createAndSaveLink("profile", ProfileView.class));

        add(new Label("userID", getCheeseSession().getAddress().getName()));

        add(new Link("logout") {
            @Override
            public void onClick() {
                getCheeseSession().logout();
                setResponsePage(LoginPage.class);
            }
        });
    }

    public void setActiveLink(String containerId) {
        WebMarkupContainer container = linksMap.get(containerId);
        if (container != null) {
            container.add(new SimpleAttributeModifier("class", "active"));
        }
    }

    private WebMarkupContainer createAndSaveLink(String id, final Class<? extends Page> responsePage) {
        Link link = new Link(id + "_link") {
            @Override
            public void onClick() {
                try {
                    setResponsePage(responsePage.newInstance());
                } catch (InstantiationException e) {
                    System.out.println("InstantiationException when creating response page");
                } catch (IllegalAccessException e) {
                    System.out.println("IllegalAccessException when creating response page");
                }
            }
        };
        WebMarkupContainer container = new WebMarkupContainer(id);
        container.add(link);
        add(container);
        linksMap.put(id, container);
        return container;
    }
}
