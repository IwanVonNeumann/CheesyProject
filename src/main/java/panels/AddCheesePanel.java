package panels;

import domain.Cheese;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

import views.CheesesView;

public class AddCheesePanel extends CheesePanel {

    public AddCheesePanel(String id) {
        super(id);

        Cheese cheese = new Cheese();

        Form form = new Form("form", new CompoundPropertyModel(cheese));

        add(form);

        form.add(new TextField("name"));
        form.add(new TextField("description"));
        form.add(new TextField("price"));

        form.add(new Button("add"){
            @Override
            public void onSubmit() {
                System.out.println("Add Cheese clicked;");
                getCheeseSession().getDataCache().addCheese(
                        (Cheese)getParent().getModelObject());
                setResponsePage(CheesesView.class);
            }
        });
    }
}