package views;

import panels.CheesesListPanel;
import panels.ShoppingCartPanel;

public class StoreView extends BasicView {

    private ShoppingCartPanel shoppingCartPanel;

    public StoreView() {

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