package panels;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

/**
 * Created by IRuskevich on 14.26.2.
 */
public class ProfileDataPanel extends CheesePanel {

    public ProfileDataPanel(String id, IModel model) {
        super(id, model);

        add(new Label("name"));
        add(new Label("street"));
        add(new Label("zipCode"));
        add(new Label("city"));
    }
}
