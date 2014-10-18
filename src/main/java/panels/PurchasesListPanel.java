package panels;

import domain.Address;
import domain.Cart;
import look.CurrencyLabel;
import look.RowModifier;
import look.proxy.CartViewProxy;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import war.CheeseApplication;

import java.io.Serializable;

/**
 * Created by IRuskevich on 14.25.2
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
                CheeseApplication.get().getModelLoader().getCartsModel() :
                // список покупок текущего пользователя
                new Model((Serializable) address.getPurchases());


        PageableListView customers =
                new PageableListView("carts", cartsModel, 10) {
                    @Override
                    protected void populateItem(ListItem listItem) {
                        Cart cart = (Cart) listItem.getModelObject();
                        listItem.setModel(
                                new CompoundPropertyModel(
                                        new CartViewProxy(cart)));

                        Label customerName = new Label("customer");

                        if (address != null) {
                            customerName.setVisible(false);
                        }
                        listItem.add(customerName);

                        Label deleted = new Label("deleted", "profile deleted");
                        if (!cart.getAddress().isDeleted()) deleted.setVisible(false);
                        listItem.add(deleted);

                        listItem.add(new Label("date"));
                        listItem.add(new Label("time"));

                        // TODO: возможно, не используется свойство LoadableDetachable
                        listItem.add(new ListView("entries", cart.getEntries()) {
                            @Override
                            protected void populateItem(ListItem listItem) {
                                CompoundPropertyModel multiCheeseModel =
                                        new CompoundPropertyModel(listItem.getModel());
                                listItem.setModel(multiCheeseModel);

                                int i = listItem.getIndex() + 1;
                                listItem.add(new RowModifier(i));
                                listItem.add(new Label("num", String.valueOf(i)));
                                listItem.add(new Label("cheese.name"));
                                listItem.add(new CurrencyLabel("price"));
                                listItem.add(new Label("quantity"));
                                listItem.add(new CurrencyLabel("cost"));
                            }
                        });

                        listItem.add(new CurrencyLabel("total", new Model(cart.getTotal())));
                    }
                };
        add(customers);

        add(new PagingNavigator("navigator", customers));
    }
}