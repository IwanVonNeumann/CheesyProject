package panels;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import search.SearchResult;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Iwan on 29.04.2014
 *
 */
public class SearchResultsPanel extends CheesePanel {

    protected SearchResultsPanel(String id, List<SearchResult> searchResults) {
        super(id);

        if (searchResults == null) {
            this.setVisible(false);
            searchResults = new LinkedList<>();
        }

        Label resultsCount = new Label("total",
                "Results found: " + searchResults.size());
        add(resultsCount);

        ListView resultsList =
                new ListView("results", new Model((java.io.Serializable) searchResults)) {
                    @Override
                    protected void populateItem(ListItem listItem) {
                        SearchResult searchResult =
                                (SearchResult)listItem.getModelObject();
                        listItem.add(new Label("resultName", searchResult.getFormattedName())
                                .setEscapeModelStrings(false));
                        listItem.add(new Label("resultDescription", searchResult.getFormattedDescription())
                                .setEscapeModelStrings(false));
                    }
                };

        add(resultsList);
    }
}