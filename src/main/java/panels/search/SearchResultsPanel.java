package panels.search;

import domain.Cart;
import look.CurrencyLabel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import panels.CheesePanel;
import search.SearchResult;
import search.SearchResultsSet;

/**
 * Created by Iwan on 29.04.2014
 */
public class SearchResultsPanel extends CheesePanel {

    protected SearchResultsPanel(String id, SearchResultsSet searchResults) {
        super(id);

        if (searchResults == null) {
            this.setVisible(false);
            searchResults = new SearchResultsSet();
        }

        Label resultsCount = new Label("total",
                "Results found: " + searchResults.size());
        add(resultsCount);

        ListView resultsList =
                new ListView("results", new Model(
                        (java.io.Serializable) searchResults.getSearchResults())) {
                    @Override
                    protected void populateItem(ListItem listItem) {
                        SearchResult searchResult =
                                (SearchResult) listItem.getModelObject();
                        listItem.add(new Label("resultName", searchResult.getFormattedName())
                                .setEscapeModelStrings(false));
                        listItem.add(new Label("resultDescription", searchResult.getFormattedDescription())
                                .setEscapeModelStrings(false));
                        listItem.add(new CurrencyLabel("price", new Model(
                                searchResult.getCheese().getPrice())));

                        listItem.add(new AjaxFallbackLink("add", listItem.getModel()) {
                            @Override
                            public void onClick(AjaxRequestTarget target) {
                                getCart().addCheese(
                                        ((SearchResult) getModelObject()).getCheese());
                                if (target != null) {
                                    //class org.apache.wicket.markup.html.list.ListItem
                                    //class panels.SearchResultsPanel$1
                                    //class panels.SearchResultsPanel
                                    //class panels.SearchPanel
                                    //class views.SearchPage
                                    target.addComponent(getParent().getParent().getParent().
                                            getParent().getParent().get("shoppingCart"));
                                }
                            }
                        });
                    }
                };

        add(resultsList);
    }

    protected Cart getCart() {
        return getCheeseSession().getCart();
    }
}