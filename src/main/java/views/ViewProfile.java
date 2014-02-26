package views;

import domain.Address;
import org.apache.wicket.markup.html.basic.Label;
import panels.PageHeadPanel;
import panels.PurchasesListPanel;
import war.CheesePage;

/**
 * Created by IRuskevich on 14.25.2.
 */
public class ViewProfile extends CheesePage {

    public ViewProfile() {
        add(new PageHeadPanel("head"));

        Address address = getCheeseSession().getAddress();

        add(new Label("name", address.getName()));
        add(new Label("street", address.getStreet()));
        add(new Label("zipCode", address.getZipCode().toString()));
        add(new Label("city", address.getCity()));

        add(new PurchasesListPanel("purchases", address));
    }

}
