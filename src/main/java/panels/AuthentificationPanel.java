package panels;

import domain.Address;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import war.Index;

/**
 * Created by Iwan on 14.1.3.
 */
public class AuthentificationPanel extends CheesePanel {

    String username = new String();
    String password = new String();

    public AuthentificationPanel(String id) {
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
                        getAddressDAO().getAddress(username);
                if ((address != null) & (address.correctHash(password))) {
                    getCheeseSession().setAddress(address);
                    setResponsePage(Index.class);
                }
            }
        });

    }
}
