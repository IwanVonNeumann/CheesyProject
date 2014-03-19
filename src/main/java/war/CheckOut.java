package war;

import domain.Address;
import domain.Cart;
import domain.MultiCheese;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import panels.PageHeadPanel;
import panels.ShoppingCartPanel;

public class CheckOut extends CheesePage {

    public CheckOut() {

        add(new PageHeadPanel("head"));

        Address address = getCheeseSession().getAddress();

        add(new Label("name", address.getName()));
        add(new Label("street", address.getStreet()));
        add(new Label("zipCode", address.getZipCode().toString()));
        add(new Label("city", address.getCity()));

        add(new Link("cancel") {
            @Override
            public void onClick() {
                setResponsePage(Index.class);
            }
        });

        //form.add(new Button("order") {
        //add(new Button("order") {
        add(new Link("order") {
            @Override
            //public void onSubmit() {
            public void onClick() {
                Cart cart = getCart();
                // обслуживание
                cart.order();

                // TODO сделать сохранение корзины и всех товаров как одну операцию
                getCheeseSession().getCartDAO().insertCart(cart);

                for (MultiCheese cheese : cart.getCheeses()) {
                    getCheeseSession().getCartEntryDAO().insertCartEntry(
                            cart, cheese);
                }

                // сброс корзины
                cart.reset();
                setResponsePage(Index.class);
            }
        });

        add(new ShoppingCartPanel("shoppingCart", getCart()));

         /*
        add(new FeedbackPanel("feedback"));

        Form form = new Form("form");
        add(form);

        address = getCart().getAddress();

        form.add(new TextField("name",
                new PropertyModel(address, "name")).setRequired(true));

        form.add(new TextField("street",
                new PropertyModel(address, "street")).setRequired(true));

        form.add(new TextField("zipCode",
                new PropertyModel(address, "zipCode")).setRequired(true));

        form.add(new TextField("city",
                new PropertyModel(address, "city")).setRequired(true));

        form.add(new Link("cancel") {
            @Override
            public void onClick() {
                setResponsePage(Index.class);
            }
        });
        */
    }

}
