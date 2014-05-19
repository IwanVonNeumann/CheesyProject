package panels;

import domain.Address;
import domain.Title;
import look.RequiredTextField;
import look.proxy.AddressViewProxy;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import views.ProfileView;

public class EditProfilePanel extends CheesePanel {

    public EditProfilePanel(String id, IModel model) {
        super(id);

        Address address = (Address)model.getObject();
        AddressViewProxy addressViewProxy = new AddressViewProxy(address);

        Form form = new Form("form",
                new CompoundPropertyModel(addressViewProxy));
        add(form);

        form.add(new DropDownChoice("title",
                Title.toStringArray()).setRequired(true));
        form.add(new RequiredTextField("name"));
        form.add(new RequiredTextField("street"));
        form.add(new RequiredTextField("zipCode"));
        form.add(new RequiredTextField("city"));

        form.add(new AjaxFallbackLink("cancel") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                EditProfilePanel editProfilePanel =
                        getEditProfilePanel();
                ProfileDataPanel profileDataPanel =
                        getProfileView().getProfileDataPanel();

                editProfilePanel.setVisible(false);
                profileDataPanel.setVisible(true);

                if (target != null) {
                    target.addComponent(editProfilePanel);
                    target.addComponent(profileDataPanel);
                }
            }
        });

        form.add(new AjaxSubmitLink("save") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form form) {
                super.onSubmit();

                Address address = ((AddressViewProxy) form.getModelObject()).getAddress();
                getCheeseSession().getDataCache().updateAddress(address);

                EditProfilePanel editProfilePanel = getEditProfilePanel();
                editProfilePanel.setVisible(false);

                ProfileDataPanel profileDataPanel =
                        getProfileView().getProfileDataPanel();

                profileDataPanel.setVisible(true);

                if (target != null) {
                    target.addComponent(editProfilePanel);
                    target.addComponent(profileDataPanel);
                }
            }
        });
    }

    private EditProfilePanel getEditProfilePanel() {
        return this;
    }

    private ProfileView getProfileView() {
        return (ProfileView)getParent();
    }
}