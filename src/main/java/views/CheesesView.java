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

        AddCheesePanel addCheesePanel = new AddCheesePanel("addCheesePanel");
        addCheesePanel.setOutputMarkupId(true);
        add(addCheesePanel);

        EditCheesePanel editCheesePanel = new EditCheesePanel("editCheesePanel", cheese);
        editCheesePanel.setOutputMarkupId(true);
        add(editCheesePanel);
    }
}
