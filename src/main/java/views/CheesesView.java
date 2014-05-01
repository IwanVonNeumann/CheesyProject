package views;

import domain.Cheese;
import panels.AddCheesePanel;
import panels.AdminCheesesListPanel;
import panels.EditCheesePanel;
import panels.PageHeadPanel;
import war.CheesePage;

public class CheesesView extends CheesePage {

    public CheesesView() {
        this(new Cheese());
    }

    public CheesesView(Cheese cheese) {
        add(new PageHeadPanel("head"));
        add(new AdminCheesesListPanel("cheesesListPanel"));
        add(new AddCheesePanel("addCheesePanel"));
        add(new EditCheesePanel("editCheesePanel", cheese));
    }
}
