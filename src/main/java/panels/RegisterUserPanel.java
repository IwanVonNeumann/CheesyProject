package panels;

import domain.Address;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import war.Index;
import war.LoginPage;

/**
 * Created by IRuskevich on 14.28.2.
 */
public class RegisterUserPanel extends CheesePanel {

    public RegisterUserPanel(String id, IModel model) {
        super(id);

        Form form = new Form("form", model);
        add(form);

        form.add(new TextField("name"));
        form.add(new TextField("street"));
        form.add(new TextField("zipCode"));
        form.add(new TextField("city"));

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
                Address address = (Address)getParent().getModelObject();
                getCheeseSession().getAddressDAO().insertAddress(address);
                getCheeseSession().setAddress(address);
                setResponsePage(Index.class);
            }
        });

    }
}
