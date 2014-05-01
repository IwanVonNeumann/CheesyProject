package views;

import panels.PageHeadPanel;
import panels.PurchasesListPanel;
import war.CheesePage;

public class PurchasesView extends CheesePage {

    public PurchasesView() {
        add(new PageHeadPanel("head"));
        add(new PurchasesListPanel("purchases"));
    }
}
