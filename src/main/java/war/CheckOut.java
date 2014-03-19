package war;

import domain.Address;
import domain.Cart;
import domain.MultiCheese;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import panels.PageHeadPanel;
import panels.ShoppingCartPanel;

import java.sql.Timestamp;

public class CheckOut extends CheesePage {

    //private Address address;

    public CheckOut() {

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
                cart.setTime(new Timestamp(System.currentTimeMillis()));
                getCheeseSession().getCartDAO().insertCart(cart);

                for (MultiCheese cheese : cart.getCheeses()) {
                    getCheeseSession().getCartEntryDAO().insertCartEntry(
                            cart, cheese);
                }

                // сброс корзины
                cart.getCheeses().clear();
                setResponsePage(Index.class);
            }
        });

        add(new ShoppingCartPanel("shoppingCart", getCart()));
    }

}
