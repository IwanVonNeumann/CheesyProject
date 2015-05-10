package panels;

import domain.CartEntry;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import panels.component.ShoppingCartPanel;

public class ArticleCounter extends CheesePanel {

    public ArticleCounter(String id, CartEntry cheese) {
        super(id);

        add(new AjaxFallbackLink("decLink", new Model(cheese)) {
            @Override
            public void onClick(AjaxRequestTarget target) {
                CartEntry selected = (CartEntry) getModelObject();
                selected.decQuantity();

                if (target != null) {
                    target.addComponent(getShoppingCartPanel()); // точно работает
                    // TODO: почему не работает?
                    // target.addComponent(getArticleCounter());
                }
            }
        });

        add(new Label("quantity", new Model(cheese.getQuantity())));

        add(new AjaxFallbackLink("incLink", new Model(cheese)) {
            @Override
            public void onClick(AjaxRequestTarget target) {
                CartEntry selected = (CartEntry)getModelObject();
                selected.incQuantity();

                if (target != null) {
                    target.addComponent(getShoppingCartPanel()); // точно работает
                    // target.addComponent(getArticleCounter()); // не работает
                }
            }
        });
    }

    private ShoppingCartPanel getShoppingCartPanel() {
        return (ShoppingCartPanel)getParent().getParent().getParent();
    }
}