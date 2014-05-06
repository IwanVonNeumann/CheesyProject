package views;

import domain.Address;
import org.apache.wicket.model.CompoundPropertyModel;
import panels.*;
import war.CheesePage;

public class ProfileView extends CheesePage {

    private ProfileDataPanel profileDataPanel;
    private EditProfilePanel editProfilePanel;
    private ChangePasswordPanel changePasswordPanel;
    private AdditionalDataPanel additionalDataPanel;

    public ProfileView() {
        add(new PageHeadPanel("head"));

        Address address = getCheeseSession().getAddress();

        /*add(new AddressDataPanel("profile",
                new CompoundPropertyModel(address)));*/

        profileDataPanel = new ProfileDataPanel("profile",
                new CompoundPropertyModel(address));
        profileDataPanel.setOutputMarkupId(true);
        profileDataPanel.setOutputMarkupPlaceholderTag(true);
        add(profileDataPanel);

        editProfilePanel = new EditProfilePanel("editProfile",
                new CompoundPropertyModel(address));
        editProfilePanel.setOutputMarkupId(true);
        editProfilePanel.setOutputMarkupPlaceholderTag(true);
        editProfilePanel.setVisible(false);
        add(editProfilePanel);

        changePasswordPanel = new ChangePasswordPanel("changePassword");
        changePasswordPanel.setOutputMarkupId(true);
        changePasswordPanel.setOutputMarkupPlaceholderTag(true);
        changePasswordPanel.setVisible(false);
        add(changePasswordPanel);

        additionalDataPanel = new AdditionalDataPanel("addData",
                new CompoundPropertyModel(address));
        additionalDataPanel.setOutputMarkupId(true);
        add(additionalDataPanel);

        add(new PurchasesListPanel("purchases", address));
    }

    public ProfileView getProfileView() {
        return this;
    }

    public ProfileDataPanel getProfileDataPanel() {
        return profileDataPanel;
    }

    public EditProfilePanel getEditProfilePanel() {
        return editProfilePanel;
    }

    public ChangePasswordPanel getChangePasswordPanel() {
        return changePasswordPanel;
    }
}