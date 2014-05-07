package panels;

import domain.Address;
import domain.Title;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import views.ProfileView;

public class EditProfilePanel extends CheesePanel {

    public EditProfilePanel(String id, IModel model) {
        super(id);

        Form form = new Form("form", model);
        add(form);


        form.add(new DropDownChoice("title",
                Title.toStringArray()).setRequired(true));
        form.add(new TextField("name"));
        form.add(new TextField("street"));
        form.add(new TextField("zipCode"));
        form.add(new TextField("city"));

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

                Address address = (Address) form.getModelObject();
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