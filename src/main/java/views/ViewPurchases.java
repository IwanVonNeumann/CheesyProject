package views;

import domain.Cart;
import domain.MultiCheese;
import look.RowModifier;
import models.CartEntriesModel;
import models.CartsModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import panels.PageHeadPanel;
import war.CheesePage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ViewPurchases extends CheesePage {

    public ViewPurchases() {
        add(new PageHeadPanel("head"));

        CartsModel cartsModel = new CartsModel(
                getCheeseSession().getCartDAO());

        PageableListView customers =
                new PageableListView("carts", cartsModel, 10) {
                    @Override
                    protected void populateItem(ListItem listItem) {
                        Cart cart = (Cart) listItem.getModelObject();
                        //System.out.println("Processing Cart with ID " +
                        //        cart.getId() + "...");
                        listItem.add(new Label("customer",
                                getCheeseSession().getAddressDAO().
                                        getAddress(cart.getCustomerID()).getName()));
                        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
                        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                        listItem.add(new Label("date",
                                dateFormat.format(cart.getTime())));
                        listItem.add(new Label("time",
                                timeFormat.format(cart.getTime())));

                        CartEntriesModel entriesModel =
                                new CartEntriesModel(
                                        getCheeseSession().getCartEntryDAO(), cart);

                        // возможно, неэффективно:
                        // потом сразу же еще раз полезем в базу
                        cart.setCheeses(
                                getCheeseSession().getCartEntryDAO().
                                        getCartEntries(cart));

                        listItem.add(new ListView("entries", entriesModel) {
                            @Override
                            protected void populateItem(ListItem listItem) {
                                MultiCheese cheese =
                                        (MultiCheese) listItem.getModelObject();
                                int i = listItem.getIndex() + 1;
                                listItem.add(new RowModifier(i));
                                listItem.add(new Label("num", String.valueOf(i)));
                                listItem.add(new Label("cheese", cheese.getName()));
                                listItem.add(new Label("price",
                                        String.format("$%.2f",
                                                cheese.getPrice())));
                                listItem.add(new Label("quantity",
                                        String.valueOf(cheese.getQuantity())));
                                listItem.add(new Label("cost",
                                        String.format("$%.2f",
                                                cheese.getCost())));
                            }
                        });

                        listItem.add(new Label("total",
                                String.format("$%.2f", cart.getTotal())));

                    }
                };
        add(customers);
        add(new PagingNavigator("navigator", customers));
    }
}
