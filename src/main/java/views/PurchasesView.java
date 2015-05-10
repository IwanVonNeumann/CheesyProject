package views;

import panels.PurchasesListPanel;

public class PurchasesView extends BasicView {

    public PurchasesView() {

        getPageHeadPanel().setActiveLink("purchases");

        add(new PurchasesListPanel("purchases"));
    }
}
