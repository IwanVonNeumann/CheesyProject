package views;

import domain.Address;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.CompoundPropertyModel;
import panels.EditProfilePanel;
import panels.PageHeadPanel;
import panels.ProfileDataPanel;
import panels.PurchasesListPanel;
import war.CheesePage;

/**
 * Created by IRuskevich on 14.25.2.
 */
public class ViewProfile extends CheesePage {

    public ViewProfile() {
        add(new PageHeadPanel("head"));

        Address address = getCheeseSession().getAddress();

        add(new ProfileDataPanel("profile",
                new CompoundPropertyModel(address)));

/*
        add(new EditProfilePanel("editProfile",
                new CompoundPropertyModel(address)));
*/

        add(new PurchasesListPanel("purchases", address));
    }

}
