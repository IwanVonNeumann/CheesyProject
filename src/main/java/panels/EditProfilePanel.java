package panels;

import domain.Cart;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

/**
 * Created by IRuskevich on 14.26.2.
 */
public class EditProfilePanel extends CheesePanel {

    public EditProfilePanel(String id, IModel model) {
        super(id, model);

        Form form = new Form("form");
        add(form);

        form.add(new TextField("name"));
        form.add(new TextField("city"));
        form.add(new TextField("zipCode"));
        form.add(new TextField("city"));

    }
}
