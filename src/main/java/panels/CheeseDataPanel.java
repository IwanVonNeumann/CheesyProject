package panels;

import domain.Cheese;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import views.CheesesView;

/**
 * Created by Iwan on 04.05.2014
 */
public class CheeseDataPanel extends CheesePanel {

    private Form cheeseForm;
    private Label createLabel;
    private Label editLabel;

    private AjaxFallbackLink createLink;
    private AjaxSubmitLink addLink;
    private AjaxSubmitLink updateLink;


    public CheeseDataPanel(String id) {
        super(id);

        createLabel = new Label("createCaption", "Add new cheese");
        createLabel.setOutputMarkupId(true);
        createLabel.setOutputMarkupPlaceholderTag(true);

        editLabel = new Label("editCaption", "Edit cheese");
        editLabel.setOutputMarkupId(true);
        editLabel.setOutputMarkupPlaceholderTag(true);

        createLink = new AjaxFallbackLink("create") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                cheeseForm.setModel(
                        new CompoundPropertyModel(new Cheese()));

                showCreateForm(target);
            }
        };

        createLink.setOutputMarkupId(true);
        createLink.setOutputMarkupPlaceholderTag(true);
        add(createLink);

        cheeseForm = new Form("cheeseForm");

        cheeseForm.add(createLabel);
        cheeseForm.add(editLabel);

        cheeseForm.setVisible(false);
        cheeseForm.setOutputMarkupId(true);
        cheeseForm.setOutputMarkupPlaceholderTag(true);
        add(cheeseForm);

        cheeseForm.add(new TextField("name").setRequired(true));          // запрет на
        cheeseForm.add(new TextField("description").setRequired(true));   // сохнарение
        cheeseForm.add(new TextField("price").setRequired(true));         // пустого поля

        final AjaxFallbackLink cancelLink = new AjaxFallbackLink("cancel") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                hideForm(target);
            }
        };

        addLink = new AjaxSubmitLink("add") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form form) {
                Cheese cheese = (Cheese) getCheeseForm().getModelObject();
                getCheeseSession().getDataCache().addCheese(cheese);

                hideForm(target);

                if (target != null) {
                    target.addComponent(
                            getCheesesView().getAdminCheesesListPanel());
                }
            }
        };

        addLink.setOutputMarkupId(true);
        addLink.setOutputMarkupPlaceholderTag(true);

        updateLink = new AjaxSubmitLink("update") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form form) {
                Cheese cheese = (Cheese) getCheeseForm().getModelObject();
                getCheeseSession().getDataCache().updateCheese(cheese);

                hideForm(target);

                if (target != null) {
                    target.addComponent(
                            getCheesesView().getAdminCheesesListPanel());
                }
            }
        };

        updateLink.setOutputMarkupId(true);
        updateLink.setOutputMarkupPlaceholderTag(true);

        cheeseForm.add(cancelLink);
        cheeseForm.add(addLink);
        cheeseForm.add(updateLink);
    }

    public void setFormModel(IModel model) {
        cheeseForm.setModel(model);
    }

    private Form getCheeseForm() {
        return cheeseForm;
    }

    private CheesesView getCheesesView() {
        return (CheesesView)getParent();
    }

    private void showCreateForm(AjaxRequestTarget target) {
        createLink.setVisible(false);
        editLabel.setVisible(false);
        updateLink.setVisible(false);

        cheeseForm.setVisible(true);
        createLabel.setVisible(true);
        addLink.setVisible(true);

        if (target != null) {
            target.addComponent(createLink);
            target.addComponent(editLabel);
            target.addComponent(updateLink);
            target.addComponent(createLabel);
            target.addComponent(addLink);
            target.addComponent(cheeseForm);
        }
    }

    public void showEditForm(AjaxRequestTarget target) {
        createLink.setVisible(false);
        createLabel.setVisible(false);
        addLink.setVisible(false);

        cheeseForm.setVisible(true);
        editLabel.setVisible(true);
        updateLink.setVisible(true);

        if (target != null) {
            target.addComponent(createLink);
            target.addComponent(editLabel);
            target.addComponent(updateLink);
            target.addComponent(createLabel);
            target.addComponent(addLink);
            target.addComponent(cheeseForm);
        }
    }

    private void hideForm(AjaxRequestTarget target) {
        cheeseForm.setVisible(false);
        createLink.setVisible(true);

        if (target != null) {
            target.addComponent(cheeseForm);
            target.addComponent(createLink);
        }
    }
}