package panels;

import domain.CartEntry;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class ArticleCounter extends Panel {

    public ArticleCounter(String id, CartEntry cheese) {
        super(id);

        add(new Link("decLink", new Model(cheese)) {
            @Override
            public void onClick() {
                CartEntry selected = (CartEntry)getModelObject();
                selected.decQuantity();
            }
        });

        add(new Label("quantity",
                "x" + cheese.getQuantity()));

        add(new Link("incLink", new Model(cheese)) {
            @Override
            public void onClick() {
                CartEntry selected = (CartEntry)getModelObject();
                selected.incQuantity();
            }
        });
    }
}
