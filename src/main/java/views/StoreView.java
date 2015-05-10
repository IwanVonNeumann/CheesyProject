package views;

import panels.CheesesListPanel;
import panels.component.ShoppingCartPanel;

public class StoreView extends BasicView {

    private ShoppingCartPanel shoppingCartPanel;

    public StoreView() {

        getPageHeadPanel().setActiveLink("store");

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