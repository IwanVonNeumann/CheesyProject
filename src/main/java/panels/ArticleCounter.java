package panels;

import domain.CartEntry;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

public class ArticleCounter extends CheesePanel {

    public ArticleCounter(String id, CartEntry cheese) {
        super(id);

        add(new AjaxFallbackLink("decLink", new Model(cheese)) {
            @Override
            public void onClick(AjaxRequestTarget target) {
                CartEntry selected = (CartEntry) getModelObject();
                selected.decQuantity();

                //class panels.ArticleCounter
                //class org.apache.wicket.markup.html.list.ListItem
                //class panels.ShoppingCartPanel$1
                //class panels.ShoppingCartPanel

                if (target != null) {
                    target.addComponent(getParent().getParent().getParent().getParent());
                }
            }
        });

        add(new Label("quantity",
                "x" + cheese.getQuantity()));

        add(new AjaxFallbackLink("incLink", new Model(cheese)) {
            @Override
            public void onClick(AjaxRequestTarget target) {
                CartEntry selected = (CartEntry)getModelObject();
                selected.incQuantity();

                if (target != null) {
                    target.addComponent(getParent().getParent().getParent().getParent());
                }
            }
        });
    }
}
