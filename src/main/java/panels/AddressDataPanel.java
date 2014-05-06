package panels;

import domain.Title;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Iwan on 05.05.2014
 */
public class AddressDataPanel extends CheesePanel {

    public AddressDataPanel(String id, IModel model) {
        super(id, model);

        final List<Component> displayComponents = new LinkedList<>();
        final List<Component> editComponents = new LinkedList<>();

        Label titleLabel = new Label("title");
        Label nameLabel = new Label("name");
        Label streetLabel = new Label("street");
        Label zipCodeLabel = new Label("zipCode");
        Label cityLabel = new Label("city");

        displayComponents.add(titleLabel);
        displayComponents.add(nameLabel);
        displayComponents.add(streetLabel);
        displayComponents.add(zipCodeLabel);
        displayComponents.add(cityLabel);

        add(titleLabel);
        add(nameLabel);
        add(streetLabel);
        add(zipCodeLabel);
        add(cityLabel);

        Form addressForm = new Form("form", model);
        addressForm.setVisible(false);

        editComponents.add(addressForm);

        /*CompoundPropertyModel addressModel = (CompoundPropertyModel) model;
        addressForm.setModel(addressModel);*/

        addressForm.add(new DropDownChoice("title",
                Title.toStringArray()).setRequired(true));
        addressForm.add(new TextField("name"));
        addressForm.add(new TextField("street"));
        addressForm.add(new TextField("zipCode"));
        addressForm.add(new TextField("city"));

        add(addressForm);

        AjaxFallbackLink editLink = new AjaxFallbackLink("edit") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                System.out.println("editLink clicked;");
                setComponentsVisibility(displayComponents, false, target);
                setComponentsVisibility(editComponents, true, target);
            }
        };

        displayComponents.add(editLink);

        add(editLink);

        ajaxify(displayComponents);
        ajaxify(editComponents);
    }

    private void setComponentsVisibility(
            List<Component> components, boolean visibility, AjaxRequestTarget target) {

        for (Component component : components) {
            component.setVisible(visibility);
        }
        if (target != null)
            for (Component component : components) {
                target.addComponent(component);
            }
    }

    private void ajaxify(List<Component> components) {
        for (Component component : components) {
            component.setOutputMarkupId(true);
            component.setOutputMarkupPlaceholderTag(true);
        }
    }
}