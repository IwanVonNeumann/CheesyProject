package views;

import domain.Cheese;
import panels.AddCheesePanel;
import panels.AdminCheesesListPanel;
import panels.EditCheesePanel;
import panels.PageHeadPanel;
import war.CheesePage;

public class ViewCheeses extends CheesePage {

    public ViewCheeses() {
        this(new Cheese());
    }

    public ViewCheeses(Cheese cheese) {
        add(new PageHeadPanel("head"));
        add(new AdminCheesesListPanel("cheesesListPanel"));
        add(new AddCheesePanel("addCheesePanel"));
        add(new EditCheesePanel("editCheesePanel", cheese));
    }
}
