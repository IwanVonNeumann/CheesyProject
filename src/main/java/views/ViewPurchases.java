package views;

import domain.Cart;
import domain.MultiCheese;
import look.RowModifier;
import models.CartEntriesModel;
import models.CartsModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import panels.PageHeadPanel;
import panels.PurchasesListPanel;
import war.CheesePage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ViewPurchases extends CheesePage {

    public ViewPurchases() {
        add(new PageHeadPanel("head"));
        add(new PurchasesListPanel("purchases"));
    }
}
