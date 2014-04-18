package war;

import org.apache.wicket.markup.html.link.Link;
import panels.CheesesListPanel;
import panels.PageHeadPanel;
import panels.ShoppingCartPanel;

public class Index extends CheesePage {

    public Index() {

        add(new PageHeadPanel("head"));

        add(new CheesesListPanel("cheesesListPanel"));

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
