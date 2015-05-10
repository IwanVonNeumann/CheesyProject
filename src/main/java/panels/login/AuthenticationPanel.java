package panels.login;

import domain.Address;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;
import panels.CheesePanel;
import views.StoreView;
import war.SignupPage;

/**
 * Created by Iwan on 14.1.3
 */

public class AuthenticationPanel extends CheesePanel {

    String username;
    String password;

    public AuthenticationPanel(String id) {
        super(id);

        Form form = new Form("form");
        add(form);

        form.add(new TextField("name",
                new PropertyModel(this, "username")).
                setRequired(true));

        form.add(new PasswordTextField("password",
                new PropertyModel(this, "password")).
                setRequired(true));

        form.add(new SubmitLink("enter") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                Address address = getCheeseSession().
                        getDataCache().getAddress(username);
                if ((address != null) && (address.correctHash(password))) {
                    getCheeseSession().setAddress(address);
                    setResponsePage(StoreView.class);
                }
            }
        });

        add(new Link("signup") {
            @Override
            public void onClick() {
                setResponsePage(SignupPage.class);
            }
        });
    }
}