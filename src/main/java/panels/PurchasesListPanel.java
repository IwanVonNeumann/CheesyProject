package panels;

import domain.Address;
import domain.Cart;
import domain.MultiCheese;

import look.RowModifier;

import models.CartsModel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.io.Serializable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by IRuskevich on 14.25.2.
 */
public class PurchasesListPanel extends CheesePanel {

    public PurchasesListPanel(String id) {
        this(id, null);
    }

    public PurchasesListPanel(String id, final Address address) {
        super(id);

        // передан ли конкретный покупатель в качестве параметра?
        IModel cartsModel = address == null ?
                // полный список из базы
                new CartsModel(getCheeseSession().getDataCache()) :
                // список покупок текущего пользователя
                new Model((Serializable) address.getPurchases());


        PageableListView customers =
                new PageableListView("carts", cartsModel, 10) {
                    @Override
                    protected void populateItem(ListItem listItem) {
                        Cart cart = (Cart) listItem.getModelObject();
                        Address customer = cart.getAddress();

                        Label customerName = new Label("customer", customer.getName());

                        if (address != null) {
                            customerName.setVisible(false);
                        }
                        listItem.add(customerName);

                        Label deleted = new Label("deleted", "profile deleted");
                        if (!customer.isDeleted()) deleted.setVisible(false);
                        listItem.add(deleted);

                        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
                        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                        listItem.add(new Label("date",
                                dateFormat.format(cart.getTime())));
                        listItem.add(new Label("time",
                                timeFormat.format(cart.getTime())));

                        listItem.add(new ListView("entries", cart.getCheeses()) {
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
