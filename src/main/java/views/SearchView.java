package views;

import panels.SearchPanel;
import panels.ShoppingCartPanel;
import search.SearchResultsSet;

/**
 * Created by Iwan on 28.04.2014
 */
public class SearchView extends BasicView {

    public SearchView(SearchResultsSet searchResults) {

        add(new SearchPanel("search", searchResults));

        ShoppingCartPanel shoppingCart =
                new ShoppingCartPanel("shoppingCart", getCart());

        shoppingCart.setOutputMarkupId(true);
        shoppingCart.setOutputMarkupPlaceholderTag(true);
        add(shoppingCart);
    }

    public SearchView() {
        this(null);
    }

}
