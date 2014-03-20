package panels;

import domain.Address;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import war.Index;
import war.LoginPage;

/**
 * Created by IRuskevich on 14.28.2
 */
public class RegisterUserPanel extends CheesePanel {

    String password1 = new String();
    String password2 = new String();

    public RegisterUserPanel(String id, IModel model) {
        super(id);

        Form form = new Form("form", model);
        add(form);

        form.add(new TextField("name").setRequired(true));
        form.add(new TextField("street").setRequired(true));
        form.add(new TextField("zipCode").setRequired(true));
        form.add(new TextField("city").setRequired(true));

        form.add(new PasswordTextField("password1",
                new PropertyModel(this, "password1")).
                setRequired(true));
        form.add(new PasswordTextField("password2",
                new PropertyModel(this, "password2")).
                setRequired(true));

        form.add(new Link("back") {
            @Override
            public void onClick() {
                setResponsePage(LoginPage.class);
            }
        });

        form.add(new SubmitLink("register") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                Address address = (Address) getParent().getModelObject();
                address.setPassword(password1);
                getCheeseSession().getDataCache().insertAddress(address);
                getCheeseSession().setAddress(address);
                setResponsePage(Index.class);
            }
        });

    }
}
