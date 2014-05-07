package panels;

import domain.Address;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.model.CompoundPropertyModel;
import security.PasswordChange;
import views.ProfileView;

/**
 * Created by Iwan on 14.1.3
 */
public class ChangePasswordPanel extends CheesePanel {

    public ChangePasswordPanel(String id) {
        super(id);

        final Form form = new Form("form",
                new CompoundPropertyModel(new PasswordChange()));
        add(form);

        form.add(new PasswordTextField("oldPassword").
                setRequired(true));
        form.add(new PasswordTextField("newPassword1").
                setRequired(true));
        form.add(new PasswordTextField("newPassword2").
                setRequired(true));

        form.add(new AjaxFallbackLink("cancel") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                ChangePasswordPanel changePasswordPanel =
                        getChangePasswordPanel();
                changePasswordPanel.setVisible(false);

                if (target != null) {
                    target.addComponent(changePasswordPanel);
                }
            }
        });

        form.add(new AjaxSubmitLink("change") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form form) {
                super.onSubmit();
                Address address = getCheeseSession().getAddress();
                PasswordChange passwordChange =
                        (PasswordChange) form.getModelObject();

                if (address.correctHash(passwordChange.getOldPassword())) {
                    if (passwordChange.passwordsEqual()) {
                        String newPassword = passwordChange.getNewPassword1();
                        address.setPassword(newPassword);
                        getCheeseSession().getDataCache().updateAddress(address);
                        System.out.println("[Security] Password set to " + newPassword);

                        ChangePasswordPanel changePasswordPanel = getChangePasswordPanel();
                        changePasswordPanel.setVisible(false);

                        AdditionalDataPanel additionalDataPanel =
                                getProfileView().getAdditionalDataPanel();

                        if (target != null) {
                            target.addComponent(changePasswordPanel);
                            target.addComponent(additionalDataPanel);
                        }
                    } else {
                        System.out.println("[Security] Passwords are not equal");
                    }
                } else {
                    System.out.println("[Security] Current password is wrong");
                }
            }
        });
    }

    private ChangePasswordPanel getChangePasswordPanel() {
        return this;
    }

    private ProfileView getProfileView() {
        return (ProfileView) getParent();
    }
}