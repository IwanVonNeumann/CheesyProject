package war;

import domain.Address;
import look.RowModifier;
import models.AddressesLDModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import panels.AuthenticationPanel;
import views.StoreView;

public class LoginPage extends CheesePage {

    public LoginPage() {

        //add(new AuthenticationPanel("authentication"));

        add(new Link("signup") {
            @Override
            public void onClick() {
                setResponsePage(SignupPage.class);
            }
        });

        AddressesLDModel addressLoadableDetachableModel = new AddressesLDModel(
                getCheeseSession().getDataCache());

        ListView customers =
                new ListView("customers", addressLoadableDetachableModel) {
                    @Override
                    protected void populateItem(ListItem listItem) {
                        int i = listItem.getIndex() + 1;
                        listItem.add(new RowModifier(i));
                        final Address address = (Address) listItem.getModelObject();

                        Link link = new Link("link") {
                            @Override
                            public void onClick() {
                                getCheeseSession().setAddress(address);
                                setResponsePage(StoreView.class);
                            }
                        };
                        link.add(new Label("name", address.getName()));
                        listItem.add(link);
                    }
                };
        add(customers);
    }
}
