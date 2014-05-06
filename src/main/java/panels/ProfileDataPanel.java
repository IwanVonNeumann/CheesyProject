package panels;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import views.ProfileView;

/**
 * Created by IRuskevich on 14.26.2
 */
public class ProfileDataPanel extends CheesePanel {

    public ProfileDataPanel(String id, IModel model) {
        super(id, model);

        add(new Label("title"));
        add(new Label("name"));
        add(new Label("street"));
        add(new Label("zipCode"));
        add(new Label("city"));

        add(new AjaxFallbackLink("edit") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                ProfileDataPanel profileDataPanel = getProfileDataPanel();
                profileDataPanel.setVisible(false);

                EditProfilePanel editProfilePanel =
                        getProfileView().getEditProfilePanel();
                editProfilePanel.setVisible(true);

                if (target != null) {
                    target.addComponent(profileDataPanel);
                    target.addComponent(editProfilePanel);
                }
            }
        });

        add(new AjaxFallbackLink("changePassword") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                ChangePasswordPanel changePasswordPanel =
                        getProfileView().getChangePasswordPanel();
                changePasswordPanel.setVisible(true);

                if (target != null) {
                    target.addComponent(changePasswordPanel);
                }
            }
        });
    }

    private ProfileDataPanel getProfileDataPanel() {
        return this;
    }

    private ProfileView getProfileView() {
        return (ProfileView) getParent();
    }
}
