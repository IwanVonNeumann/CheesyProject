package views;

import domain.Address;
import org.apache.wicket.model.CompoundPropertyModel;
import panels.*;
import war.CheesePage;

public class ViewProfile extends CheesePage {

    public ViewProfile() {
        add(new PageHeadPanel("head"));

        Address address = getCheeseSession().getAddress();

        add(new ProfileDataPanel("profile",
                new CompoundPropertyModel(address)));

        EditProfilePanel editProfilePanel =
                new EditProfilePanel("editProfile",
                        new CompoundPropertyModel(address));
        editProfilePanel.setVisible(false);
        add(editProfilePanel);

        add(new ChangePasswordPanel("changePassword")
                .setVisible(false));

        add(new PurchasesListPanel("purchases", address));
    }

}
