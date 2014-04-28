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
import search.SearchResult;
import static search.SearchEngine.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Iwan on 28.04.2014
 */
public class SearchPanel extends CheesePanel {

    public SearchPanel(String id) {
        super(id);

        final ValueMap searchPars = new ValueMap();
        searchPars.put("query", "");
        searchPars.put("criteria", new LinkedList());

        final WebMarkupContainer wmc = new WebMarkupContainer("wmc");

        wmc.setVisible(false);
        wmc.setOutputMarkupPlaceholderTag(true);

        ArrayList<String> criteria = new ArrayList<>();
        criteria.add("Names");
        criteria.add("Descriptions");

        wmc.add(new CheckBoxMultipleChoice("criteria", criteria)
                .setPrefix("").setSuffix("&nbsp;"));

        Form form = new Form("form",
                new CompoundPropertyModel(searchPars)) {
            @Override
            protected void onSubmit() {
                List<SearchResult> results = search(
                        getCheeseSession().getDataCache(), searchPars);
                for (SearchResult result : results) {
                    System.out.println(result);
                }
            }
        };

        add(form);

        form.add(new TextField("query"));
        form.add(wmc);

        form.add(new AjaxCheckBox("advanced",
                new PropertyModel(wmc, "visible")) {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                target.addComponent(wmc);
            }
        });
    }
}
