package views;

import domain.Address;
import org.apache.wicket.model.CompoundPropertyModel;
import panels.*;

public class ProfileView extends BasicView {

    private ProfileDataPanel profileDataPanel;
    private EditProfilePanel editProfilePanel;
    private ChangePasswordPanel changePasswordPanel;
    private AdditionalDataPanel additionalDataPanel;

    public ProfileView() {

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

    public ProfileDataPanel getProfileDataPanel() {
        return profileDataPanel;
    }

    public EditProfilePanel getEditProfilePanel() {
        return editProfilePanel;
    }

    public ChangePasswordPanel getChangePasswordPanel() {
        return changePasswordPanel;
    }

    public AdditionalDataPanel getAdditionalDataPanel() {
        return additionalDataPanel;
    }
}