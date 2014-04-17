package panels;

import domain.Cart;
import domain.CartEntry;

import look.CurrencyLabel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class ShoppingCartPanel extends CheesePanel {

    private Cart cart;

    public ShoppingCartPanel(String id, Cart cart) {
        super(id);
        this.cart = cart;

        add(new ListView("cart",
                new PropertyModel(this, "cart.cheeses")) {
            @Override
            protected void populateItem(ListItem listItem) {
                listItem.setModel(
                        new CompoundPropertyModel(
                                listItem.getModel()));

                listItem.add(new Label("name"));
                listItem.add(new ArticleCounter("articleCounter",
                        (CartEntry) listItem.getModelObject()));
                listItem.add(new CurrencyLabel("price"));

                listItem.add(new Link("remove", listItem.getModel()) {
                    @Override
                    public void onClick() {
                        getCart().removeCheese(
                                ((CartEntry) getModelObject()).getCheese());
                    }
                });
            }
        });

        add(new CurrencyLabel("total", new Model() {
            @Override
            public Object getObject() {
                return getCart().getTotal();
            }
        }));

        /*add(new Label("total", new Model() {
            public Object getObject() {
                return String.format("$%.2f", getCart().getTotal().doubleValue());
            }
        }));*/
    }

    private Cart getCart() {
        return cart;
    }
}