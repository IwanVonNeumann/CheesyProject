package panels;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;

import java.util.ArrayList;
import java.util.LinkedList;

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
                //TODO: search

                System.out.println("Advanced search options " +
                        (wmc.isVisible() ? "on..." : "off..."));

                if (wmc.isVisible()) {
                    LinkedList<String> searchCriteria =
                            (LinkedList<String>)searchPars.get("criteria");

                    if (searchCriteria.size() > 0) {
                        System.out.print("Searching by");
                        for (String criterion : searchCriteria) {
                            System.out.print(" " + criterion);
                        }
                        System.out.println("...");
                    }
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
