package views;

import domain.Address;
import org.apache.wicket.model.CompoundPropertyModel;
import panels.*;
import war.CheesePage;

public class ProfileView extends CheesePage {

    public ProfileView() {
        add(new PageHeadPanel("head"));

        Address address = getCheeseSession().getAddress();

        add(new AddressDataPanel("profile",
                new CompoundPropertyModel(address)));

        /*add(new ProfileDataPanel("profile",
                new CompoundPropertyModel(address)));

        EditProfilePanel editProfilePanel =
                new EditProfilePanel("editProfile",
                        new CompoundPropertyModel(address));
        editProfilePanel.setVisible(false);
        add(editProfilePanel);

        add(new ChangePasswordPanel("changePassword")
                .setVisible(false));*/

        add(new PurchasesListPanel("purchases", address));
    }

}
