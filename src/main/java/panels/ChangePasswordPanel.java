package panels;

import domain.Address;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.model.CompoundPropertyModel;
import security.PasswordChange;
import views.ProfileView;

/**
 * Created by Iwan on 14.1.3
 */
public class ChangePasswordPanel extends CheesePanel {

    public ChangePasswordPanel(String id) {
        super(id);

        Form form = new Form("form",
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

        form.add(new SubmitLink("change") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                Address address = getCheeseSession().getAddress();
                PasswordChange passwordChange =
                        (PasswordChange)getModelObject();
                if ((passwordChange.passwordsEqual()) &&
                        address.correctHash(
                                passwordChange.getOldPassword())) {
                    String newPassword = passwordChange.getNewPassword1();
                    address.setPassword(newPassword);
                    getCheeseSession().getDataCache().updateAddress(address);
                    System.out.println("Password set to " + newPassword);
                }
                setResponsePage(ProfileView.class);
                /*
                getParent().getParent().setVisible(false);
                getParent().getParent().getParent().
                        get("profile").setVisible(true);
                        */
            }
        });
    }

    private ChangePasswordPanel getChangePasswordPanel() {
        return this;
    }
}