package views;

import panels.CheesesListPanel;
import panels.PageHeadPanel;
import panels.ShoppingCartPanel;
import war.CheesePage;

public class StoreView extends CheesePage {

    private ShoppingCartPanel shoppingCartPanel;

    public StoreView() {

        add(new PageHeadPanel("head"));

        add(new CheesesListPanel("cheesesListPanel"));

        shoppingCartPanel = new ShoppingCartPanel("shoppingCart", getCart());

        shoppingCartPanel.setOutputMarkupId(true);
        shoppingCartPanel.setOutputMarkupPlaceholderTag(true);
        add(shoppingCartPanel);
    }

    public ShoppingCartPanel getShoppingCartPanel() {
        return shoppingCartPanel;
    }

}