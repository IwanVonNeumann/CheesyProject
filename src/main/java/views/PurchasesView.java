package views;

import panels.PurchasesListPanel;

public class PurchasesView extends BasicView {

    public PurchasesView() {
        add(new PurchasesListPanel("purchases"));
    }
}
