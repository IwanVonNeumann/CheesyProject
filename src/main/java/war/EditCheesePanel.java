package war;

import domain.Cheese;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

public class EditCheesePanel extends CheesePanel {

    private Cheese cheese;

    public EditCheesePanel(String id, final Cheese cheese) {
        super(id);

        this.cheese = cheese;

        Form form = new Form("form");
        add(form);

        form.add(new TextField("name",
                new PropertyModel(cheese, "name")));
        form.add(new TextField("description",
                new PropertyModel(cheese, "description")));
        form.add(new TextField("price",
                new PropertyModel(cheese, "price")));
        form.add(new Link("cancel"){
            @Override
            public void onClick() {
                getParent().setVisible(false);
            }
        });

        form.add(new Button("update"){
            @Override
            public void onSubmit() {
                //System.out.println("Update " +
                  //      cheese.getName() + " Cheese clicked;");
                getCheeseSession().getCheeseDAO().updateCheese(cheese);
                setResponsePage(ViewCheeses.class);
            }
        });
    }

    @Override
    public boolean isVisible() {
        return cheese.getName() != null;
    }

}
