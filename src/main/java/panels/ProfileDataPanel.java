package panels;

import domain.Address;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

/**
 * Created by IRuskevich on 14.26.2
 */
public class ProfileDataPanel extends CheesePanel {

    public ProfileDataPanel(String id, IModel model) {
        super(id, model);

        add(new Label("name"));
        add(new Label("street"));
        add(new Label("zipCode"));
        add(new Label("city"));

        add(new Link("edit") {
            @Override
            public void onClick() {
                // getParent() == ProfileDataPanel
                // getParent().getParent() == ViewProfile
                getParent().setVisible(false);
                getParent().getParent().
                        get("editProfile").setVisible(true);
            }
        });

        add(new Link("changePassword") {
            @Override
            public void onClick() {
                getParent().setVisible(false);
                getParent().getParent().
                        get("changePassword").setVisible(true);
            }
        });

        String hexadecimalHash = (
                (Address)getModelObject()).getHexHash(4, "<br/>");

        add(new Label("hash", hexadecimalHash).setEscapeModelStrings(false));
    }
}
