package views;

import org.apache.wicket.markup.html.link.Link;
import panels.CheesesListPanel;
import panels.PageHeadPanel;
import panels.ShoppingCartPanel;
import war.CheckOut;
import war.CheesePage;

public class StoreView extends CheesePage {

    public StoreView() {

        add(new PageHeadPanel("head"));

        add(new CheesesListPanel("cheesesListPanel"));

        //TODO: объединить панель с кнопкой
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
