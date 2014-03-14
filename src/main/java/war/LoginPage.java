package war;

import domain.Address;
import look.RowModifier;
import models.CustomersModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import panels.AuthentificationPanel;

public class LoginPage extends CheesePage {

    public LoginPage() {

        add(new AuthentificationPanel("enter"));

        add(new Link("signup") {
            @Override
            public void onClick() {
                setResponsePage(SignupPage.class);
            }
        });

        CustomersModel customersModel = new CustomersModel(
                getCheeseSession().getAddressDAO());

        ListView customers =
                new ListView("customers", customersModel) {
                    @Override
                    protected void populateItem(ListItem listItem) {
                        int i = listItem.getIndex() + 1;
                        listItem.add(new RowModifier(i));
                        final Address address = (Address) listItem.getModelObject();

                        Link link = new Link("link") {
                            @Override
                            public void onClick() {
                                getCheeseSession().setAddress(address);
                                setResponsePage(Index.class);
                            }
                        };
                        link.add(new Label("name", address.getName()));
                        listItem.add(link);

                    }
                };
        add(customers);


    }
}
