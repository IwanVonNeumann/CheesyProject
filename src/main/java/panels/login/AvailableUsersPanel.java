package panels.login;

import domain.Address;
import models.AddressesLDModel;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import panels.CheesePanel;
import views.StoreView;
import war.CheeseApplication;

/**
 * Created by Iwan on 09.05.2015
 */

public class AvailableUsersPanel extends CheesePanel {

    public AvailableUsersPanel(String id) {
        super(id);

        AddressesLDModel addressLoadableDetachableModel =
                (AddressesLDModel) CheeseApplication.get().getModelLoader().getAddressModel();

        ListView customers =
                new ListView("customers", addressLoadableDetachableModel) {
                    @Override
                    protected void populateItem(ListItem listItem) {
                        listItem.add(new SimpleAttributeModifier("class", "list-group-item")); // hack
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
