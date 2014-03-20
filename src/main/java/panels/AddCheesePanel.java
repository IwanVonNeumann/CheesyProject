package panels;

import domain.Cheese;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import views.ViewCheeses;

public class AddCheesePanel extends CheesePanel {

    public AddCheesePanel(String id) {
        super(id);

        final Cheese cheese = new Cheese();

        Form form = new Form("form") {
            /*
            @Override
            protected void onSubmit() {
                super.onSubmit();
                System.out.println("Cheese adding form submitted...");
            }

            @Override
            protected void onError() {
                super.onError();
                System.out.println("Error when adding a cheese...");
            }*/
        };
        add(form);

        form.add(new TextField("name",
                new PropertyModel(cheese, "name")));
        form.add(new TextField("description",
                new PropertyModel(cheese, "description")));
        form.add(new TextField("price",
                new PropertyModel(cheese, "price")));
        form.add(new Button("add"){
            @Override
            public void onSubmit() {
                System.out.println("Add Cheese clicked;");
                getCheeseSession().getDataCache().addCheese(cheese);
                setResponsePage(ViewCheeses.class);
            }
        });
    }
}
