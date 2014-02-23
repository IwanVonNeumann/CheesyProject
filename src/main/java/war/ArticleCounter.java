package war;

import domain.MultiCheese;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class ArticleCounter extends Panel {

    private final MultiCheese cheese;

    public ArticleCounter(String id, MultiCheese cheese) {
        super(id);

        this.cheese = cheese;

        add(new Link("decLink", new Model(cheese)) {
            @Override
            public void onClick() {
                MultiCheese selected = (MultiCheese)getModelObject();
                selected.decQuantity();
            }
        });

        add(new Label("quantity",
                "x" + cheese.getQuantity()));

        add(new Link("incLink", new Model(cheese)) {
            @Override
            public void onClick() {
                MultiCheese selected = (MultiCheese)getModelObject();
                selected.incQuantity();
            }
        });
    }

}
