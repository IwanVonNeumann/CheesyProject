package panels;

import domain.Address;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import security.PasswordChange;
import views.ProfileView;

/**
 * Created by Iwan on 14.1.3
 */
public class ChangePasswordPanel extends CheesePanel {

    private FeedbackPanel feedbackPanel; // TODO: починить

    public ChangePasswordPanel(String id) {
        super(id);

        final Form form = new Form("form",
                new CompoundPropertyModel(new PasswordChange()));
        add(form);

        form.add(new PasswordTextField("oldPassword").
                setRequired(true));

        PasswordTextField passwordField1 = new PasswordTextField("newPassword");
        passwordField1.setRequired(true);

        form.add(passwordField1);

        PasswordTextField passwordField2 = new PasswordTextField("controlPassword");
        passwordField2.setRequired(true);
        passwordField2.setModel(passwordField1.getModel());

        form.add(passwordField2);

        form.add(new EqualPasswordInputValidator(passwordField1, passwordField2));

        feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupId(true);
        form.add(feedbackPanel);

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
                    String newPassword = passwordChange.getNewPassword();
                    address.setPassword(newPassword);
                    getCheeseSession().getDataCache().updateAddress(address);
                    System.out.println("[Security] Password set to " + newPassword);
                    info("Password has been changed"); // не работает

                    ChangePasswordPanel changePasswordPanel = getChangePasswordPanel();
                    changePasswordPanel.setVisible(false);

                    AdditionalDataPanel additionalDataPanel =
                            getProfileView().getAdditionalDataPanel();

                    if (target != null) {
                        target.addComponent(changePasswordPanel);
                        target.addComponent(additionalDataPanel);
                        //target.addComponent(feedbackPanel);
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