package panels;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;
import search.SearchResultsSet;
import views.SearchView;

import java.util.ArrayList;
import java.util.LinkedList;

import static search.SearchEngine.search;

/**
 * Created by Iwan on 28.04.2014
 */
public class SearchPanel extends CheesePanel {


    public SearchPanel(String id, SearchResultsSet searchResults) {
        super(id);

        final ValueMap searchPars = new ValueMap();
        searchPars.put("query", "");
        searchPars.put("criteria", new LinkedList());

        final WebMarkupContainer wmc = new WebMarkupContainer("wmc");

        wmc.setVisible(false);
        wmc.setOutputMarkupPlaceholderTag(true);

        ArrayList<String> criteria = new ArrayList<>();
        criteria.add("name");
        criteria.add("description");

        wmc.add(new CheckBoxMultipleChoice("criteria", criteria)
                .setPrefix("").setSuffix("&nbsp;"));

        final SearchResultsPanel searchResultsPanel =
                new SearchResultsPanel("results", searchResults);

        Form form = new Form("form",
                new CompoundPropertyModel(searchPars)) {

            @Override
            protected void onSubmit() {
                SearchResultsSet newResults = search(
                        getCheeseSession().getDataCache(), searchPars);
                /*for (SearchResult result : newResults.getSearchResults()) {
                    System.out.println(result);
                    System.out.println(result.getFormattedName());
                    System.out.println(result.getFormattedDescription());
                }*/
                setResponsePage(new SearchView(newResults));
            }
        };

        add(form);

        form.add(new TextField("query").setRequired(true));
        form.add(wmc);

        form.add(new AjaxCheckBox("advanced",
                new PropertyModel(wmc, "visible")) {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                target.addComponent(wmc);
            }
        });

        add(searchResultsPanel);
    }

}