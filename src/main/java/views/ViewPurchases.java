package views;

import panels.PageHeadPanel;
import panels.PurchasesListPanel;
import war.CheesePage;

public class ViewPurchases extends CheesePage {

    public ViewPurchases() {
        add(new PageHeadPanel("head"));
        add(new PurchasesListPanel("purchases"));
    }
}
