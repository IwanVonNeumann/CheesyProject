package panels;

import domain.Address;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

public class EditProfilePanel extends CheesePanel {

    public EditProfilePanel(String id, IModel model) {
        super(id);

        Form form = new Form("form", model);
        add(form);

        form.add(new TextField("name"));
        form.add(new TextField("street"));
        form.add(new TextField("zipCode"));
        form.add(new TextField("city"));
        form.add(new Link("cancel") {
            @Override
            public void onClick() {
                getParent().getParent().setVisible(false);
                getParent().getParent().getParent().
                        get("profile").setVisible(true);
            }
        });
        form.add(new SubmitLink("save") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                // getParent() == form
                // getParent().getParent() == EditProfilePanel
                // getParent().getParent().getParent() == VieProfile
                getParent().getParent().setVisible(false);
                getParent().getParent().getParent().
                        get("profile").setVisible(true);
                getCheeseSession().getAddressDAO().updateAddress(
                        (Address)(getParent().getModelObject()));
            }
        });

    }
}
