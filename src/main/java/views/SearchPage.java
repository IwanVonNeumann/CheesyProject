package views;

import org.apache.wicket.markup.html.link.Link;
import panels.PageHeadPanel;
import panels.SearchPanel;
import panels.ShoppingCartPanel;
import war.CheckOut;
import war.CheesePage;

/**
 * Created by Iwan on 28.04.2014
 */
public class SearchPage extends CheesePage {

    public SearchPage() {

        add(new PageHeadPanel("head"));

        add(new SearchPanel("search"));

        ShoppingCartPanel shoppingCart =
                new ShoppingCartPanel("shoppingCart", getCart());

        shoppingCart.setOutputMarkupId(true);
        add(shoppingCart);

        add(new Link("checkout") {
            @Override
            public void onClick() {
                setResponsePage(new CheckOut());
            }

            @Override
            public boolean isVisible() {
                return !getCart().getEntries().isEmpty();
            }
        });

    }
}
