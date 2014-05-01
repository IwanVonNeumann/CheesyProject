package panels;

import domain.Cheese;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import views.CheesesView;

public class EditCheesePanel extends CheesePanel {

    private Cheese cheese;

    public EditCheesePanel(String id, Cheese cheese) {
        super(id);

        this.cheese = cheese;

        Form form = new Form("form", new CompoundPropertyModel(cheese));
        add(form);

        form.add(new TextField("name"));
        form.add(new TextField("description"));
        form.add(new TextField("price"));

        form.add(new Link("cancel") {
            @Override
            public void onClick() {
                getParent().setVisible(false);
            }
        });

        form.add(new Button("update") {
            @Override
            public void onSubmit() {
                //System.out.println("Update " +
                //      cheese.getName() + " Cheese clicked;");
                getCheeseSession().getDataCache().updateCheese(
                        (Cheese)getParent().getModelObject());
                setResponsePage(CheesesView.class);
            }
        });
    }

    @Override
    public boolean isVisible() {
        return cheese.getName() != null;
    }
}