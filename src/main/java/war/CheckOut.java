package war;

import domain.Address;
import domain.Cart;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import panels.PageHeadPanel;
import panels.ShoppingCartPanel;
import views.StoreView;

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
                setResponsePage(StoreView.class);
            }
        });

        //form.add(new Button("order") {
        //add(new Button("order") {
        add(new Link("order") {
            @Override
            //public void onSubmit() {
            public void onClick() {
                Cart cart = getCart();
                cart.order(); // обслуживание
                // TODO: проверить, работает ли на хайбернейте
                getCheeseSession().getDataCache().insertCart(cart); // сохранение в базе
                cart.reset(); // сброс корзины
                setResponsePage(StoreView.class);
            }
        });

        // TODO: manage empty checkout issue
        ShoppingCartPanel shoppingCart =
                new ShoppingCartPanel("shoppingCart", getCart());

        shoppingCart.setOutputMarkupId(true);
        shoppingCart.setOutputMarkupPlaceholderTag(true);
        add(shoppingCart);
    }
}
