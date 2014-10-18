package views;

import domain.Address;
import look.RowModifier;
import models.AddressesLDModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import war.CheeseApplication;
import war.LoginPage;

public class CustomersView extends BasicView {

    public CustomersView() {

        AddressesLDModel addressesModel =
                (AddressesLDModel) CheeseApplication.get().getModelLoader().getAddressModel();

        PageableListView customers =
                new PageableListView("customers", addressesModel, 10) {
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
                                getCheeseSession().getDataCache().
                                        safeDeleteAddress(address);

                                if (address.equals(getCheeseSession().getAddress())) {
                                    getCheeseSession().logout();
                                    setResponsePage(LoginPage.class);
                                } else {
                                    setResponsePage(CustomersView.class);
                                }
                            }
                        });
                    }
                };
        add(customers);
        add(new PagingNavigator("navigator", customers));
    }
}