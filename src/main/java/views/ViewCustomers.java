package views;

import domain.Address;
import look.RowModifier;
import models.CustomersModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import panels.PageHeadPanel;
import war.CheesePage;

public class ViewCustomers extends CheesePage {

    public ViewCustomers() {

        add(new PageHeadPanel("head"));

        CustomersModel customersModel = new CustomersModel(
                getCheeseSession().getAddressDAO());

        PageableListView customers =
                new PageableListView("customers", customersModel, 10) {
                    @Override
                    protected void populateItem(ListItem listItem) {
                        Address address = (Address) listItem.getModelObject();
                        final int i = listItem.getIndex() + 1;
                        listItem.add(new RowModifier(i));
                        listItem.add(new Label("num", String.valueOf(i)));
                        listItem.add(new Label("name", address.getName()));
                        listItem.add(new Label("street", address.getStreet()));
                        listItem.add(new Label("zip", address.getZipCode().toString()));
                        listItem.add(new Label("city", address.getCity()));
                    }
                };
        add(customers);
        add(new PagingNavigator("navigator", customers));
    }
}