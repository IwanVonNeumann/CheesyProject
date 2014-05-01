package panels;

import domain.Address;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;
import views.ProfileView;

/**
 * Created by Iwan on 14.1.3
 */
public class ChangePasswordPanel extends CheesePanel {

    String oldPassword;
    String password1;
    String password2;

    public ChangePasswordPanel(String id) {
        super(id);

        Form form = new Form("form");
        add(form);

        form.add(new PasswordTextField("oldPassword",
                new PropertyModel(this, "oldPassword")).
                setRequired(true));
        form.add(new PasswordTextField("password1",
                new PropertyModel(this, "password1")).
                setRequired(true));
        form.add(new PasswordTextField("password2",
                new PropertyModel(this, "password2")).
                setRequired(true));

        form.add(new Link("cancel") {
            @Override
            public void onClick() {
                getParent().getParent().setVisible(false);
                getParent().getParent().getParent().
                        get("profile").setVisible(true);
            }
        });

        form.add(new SubmitLink("change") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                Address address = getCheeseSession().getAddress();
                if (address.correctHash(oldPassword)
                        & (password1.equals(password2))) {
                    address.setPassword(password1);
                    getCheeseSession().getDataCache().updateAddress(address);
                    System.out.println("Password set to " + password1);
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
}
