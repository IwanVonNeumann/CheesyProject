package views;

import panels.search.SearchPanel;
import panels.component.ShoppingCartPanel;
import search.SearchResultsSet;

/**
 * Created by Iwan on 28.04.2014
 */
public class SearchView extends BasicView {

    public SearchView(SearchResultsSet searchResults) {

        getPageHeadPanel().setActiveLink("search");

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
