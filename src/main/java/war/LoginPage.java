package war;

import domain.Address;
import models.CustomersModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

public class LoginPage extends CheesePage {

    public LoginPage() {
        CustomersModel customersModel = new CustomersModel(
                getCheeseSession().getAddressDAO());

        ListView customers =
                new ListView("customers", customersModel) {
                    @Override
                    protected void populateItem(ListItem listItem) {
                        final Address address = (Address) listItem.getModelObject();
                        /*
                        final int i = listItem.getIndex() + 1;
                        //listItem.add(new RowModifier(i));
                        listItem.add(new Label("num", String.valueOf(i)));
                        */
                        Link link = new Link("link") {
                            @Override
                            public void onClick() {
                                getCheeseSession().setAddress(address);
                                setResponsePage(Index.class);
                            }
                        };
                        link.add(new Label("name", address.getName()));
                        listItem.add(link);
                        /*
                        listItem.add(new Label("street", address.getStreet()));
                        listItem.add(new Label("zip", address.getZipCode().toString()));
                        listItem.add(new Label("city", address.getCity()));
                        */
                    }
                };
        add(customers);
    }
}
