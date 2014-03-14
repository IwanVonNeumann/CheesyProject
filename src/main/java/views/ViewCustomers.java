package views;

import domain.Address;
import domain.Cheese;
import domain.MultiCheese;
import look.RowModifier;
import models.CustomersModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import panels.PageHeadPanel;
import war.CheesePage;
import war.LoginPage;

public class ViewCustomers extends CheesePage {

    public ViewCustomers() {

        add(new PageHeadPanel("head"));

        CustomersModel customersModel = new CustomersModel(
                getCheeseSession().getAddressDAO());

        PageableListView customers =
                new PageableListView("customers", customersModel, 10) {
                    @Override
                    protected void populateItem(ListItem listItem) {
                        final Address address = (Address) listItem.getModelObject();
                        final int i = listItem.getIndex() + 1;
                        listItem.add(new RowModifier(i));
                        listItem.add(new Label("num", String.valueOf(i)));
                        listItem.add(new Label("name", address.getName()));
                        listItem.add(new Label("street", address.getStreet()));
                        listItem.add(new Label("zip", address.getZipCode().toString()));
                        listItem.add(new Label("city", address.getCity()));

                        listItem.add(new Link("delete", listItem.getModel()) {
                            @Override
                            public void onClick() {
                                //System.out.println("Deleting Customer clicked...");

                                //удаление из базы
                                //getCheeseSession().getAddressDAO().deleteAddress(address);
                                getCheeseSession().getAddressDAO().
                                        safeDeleteAddress(address);

                                if (address.equals(getCheeseSession().getAddress())) {
                                    getCheeseSession().logout();
                                    setResponsePage(LoginPage.class);
                                } else {
                                    setResponsePage(ViewCustomers.class);
                                }
                            }
                        });
                    }
                };
        add(customers);
        add(new PagingNavigator("navigator", customers));
    }
}
