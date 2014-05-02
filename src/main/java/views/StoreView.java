package views;

import panels.CheesesListPanel;
import panels.PageHeadPanel;
import panels.ShoppingCartPanel;
import war.CheesePage;

public class StoreView extends CheesePage {

    public StoreView() {

        add(new PageHeadPanel("head"));

        add(new CheesesListPanel("cheesesListPanel"));

        ShoppingCartPanel shoppingCart =
                new ShoppingCartPanel("shoppingCart", getCart());

        shoppingCart.setOutputMarkupId(true);
        shoppingCart.setOutputMarkupPlaceholderTag(true);
        add(shoppingCart);
    }
}
