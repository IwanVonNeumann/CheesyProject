package war;

import domain.Cart;
import domain.Cheese;
import domain.MultiCheese;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class ShoppingCartPanel extends Panel {

    private Cart cart;

    public ShoppingCartPanel(String id, Cart cart) {
        super(id);
        this.cart = cart;

        add(new ListView("cart",
                new PropertyModel(this, "cart.cheeses")) {
            @Override
            protected void populateItem(ListItem listItem) {
                MultiCheese cheese = (MultiCheese) listItem.getModelObject();
                listItem.add(new Label("name", cheese.getName()));
                listItem.add(new ArticleCounter("articleCounter", cheese));
                listItem.add(new Label("price", "$" + cheese.getPrice().toString()));

                listItem.add(new Link("remove", listItem.getModel()) {
                    @Override
                    public void onClick() {
                        MultiCheese selected = (MultiCheese) getModelObject();
                        getCart().removeCheese(selected);
                    }
                });
            }
        });

        add(new Label("total", new Model() {
            @Override
            public Object getObject() {
                return String.format("$%.2f", getCart().getTotal().doubleValue());
            }
        }));
    }

    private Cart getCart() {
        return cart;
    }
}