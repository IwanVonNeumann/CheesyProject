package panels;

import domain.Address;
import domain.Title;
import look.RequiredTextField;
import look.proxy.AddressViewProxy;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import views.StoreView;
import war.LoginPage;

/**
 * Created by IRuskevich on 14.28.2
 */
public class RegisterUserPanel extends CheesePanel {

    String password1;
    String password2;

    public RegisterUserPanel(String id, IModel model) {
        super(id);

        Address address = (Address) model.getObject();
        AddressViewProxy addressViewProxy = new AddressViewProxy(address);

        Form form = new Form("form",
                new CompoundPropertyModel(addressViewProxy));
        add(form);

        Model titleModel = new Model("Title");
        Model nameModel = new Model("Name");
        Model streetModel = new Model("Street");
        Model zipCodeModel = new Model("Zip code");
        Model cityModel = new Model("City");
        Model passwordModel = new Model("Password");
        Model confirmPasswordModel = new Model("Confirm password");

        form.add(new Label("titleLabel", titleModel));
        form.add(new Label("nameLabel", nameModel));
        form.add(new Label("streetLabel", streetModel));
        form.add(new Label("zipCodeLabel", zipCodeModel));
        form.add(new Label("cityLabel", cityModel));
        form.add(new Label("passwordLabel", passwordModel));
        form.add(new Label("confirmPasswordLabel", confirmPasswordModel));

        address.setTitle(Title.MR);
        form.add(new DropDownChoice("title", Title.toStringArray())
                .setRequired(true)
                .setLabel(titleModel));
        form.add(new RequiredTextField("name")
                .setLabel(nameModel));
        form.add(new RequiredTextField("street")
                .setLabel(streetModel));
        form.add(new RequiredTextField("zipCode")
                .setLabel(zipCodeModel));
        form.add(new RequiredTextField("city")
                .setLabel(cityModel));

        form.add(new PasswordTextField("password1",
                new PropertyModel(this, "password1"))
                .setRequired(true)
                .setLabel(passwordModel));
        form.add(new PasswordTextField("password2",
                new PropertyModel(this, "password2"))
                .setRequired(true)
                .setLabel(confirmPasswordModel));

        form.add(new Link("back") {
            @Override
            public void onClick() {
                setResponsePage(LoginPage.class);
            }
        });

        // TODO: цивилизованно сделать регистрацию
        form.add(new SubmitLink("register") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                Address address = (Address) getParent().getModelObject();
                address.setPassword(password1);
                getCheeseSession().getDataCache().insertAddress(address);
                getCheeseSession().setAddress(address);
                setResponsePage(StoreView.class);
            }
        });

        add(new FeedbackPanel("feedback"));

    }
}