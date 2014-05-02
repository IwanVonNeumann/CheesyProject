package views;

import panels.PageHeadPanel;
import panels.SearchPanel;
import panels.ShoppingCartPanel;
import search.SearchResultsSet;
import war.CheesePage;

/**
 * Created by Iwan on 28.04.2014
 */
public class SearchView extends CheesePage {

    public SearchView(SearchResultsSet searchResults) {
        add(new PageHeadPanel("head"));

        add(new SearchPanel("search", searchResults));

        ShoppingCartPanel shoppingCart =
                new ShoppingCartPanel("shoppingCart", getCart());

        shoppingCart.setOutputMarkupId(true);
        add(shoppingCart);
    }

    public SearchView() {
        this(null);
    }

}
