package panels;

import domain.Cheese;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

public class EditCheesePanel extends CheesePanel {

    public EditCheesePanel(String id) {
        super(id);

        Form form = new Form("form",
                new CompoundPropertyModel(new Cheese()));
        add(form);

        form.add(new TextField("name"));
        form.add(new TextField("description"));
        form.add(new TextField("price"));

        form.add(new AjaxFallbackLink("cancel") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                EditCheesePanel editCheesePanel =
                        (EditCheesePanel) getParent().getParent();
                editCheesePanel.setVisible(false);

                if (target != null) {
                    target.addComponent(editCheesePanel);
                }
            }
        });

        form.add(new AjaxSubmitLink("save") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form form) {
                //System.out.println("Update " +
                //      cheese.getName() + " Cheese clicked;");
                Form parentForm = (Form) getParent();
                Cheese cheese = (Cheese) parentForm.getModelObject();
                getCheeseSession().getDataCache().updateCheese(cheese);
                EditCheesePanel editCheesePanel =
                        (EditCheesePanel) parentForm.getParent();
                editCheesePanel.setVisible(false);

                if (target != null) {
                    target.addComponent(editCheesePanel);
                    AdminCheesesListPanel adminCheesesListPanel =
                            (AdminCheesesListPanel) editCheesePanel.getParent().get("cheesesListPanel");
                    target.addComponent(adminCheesesListPanel);
                }
            }
        });
    }
}