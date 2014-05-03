package views;

import panels.AddCheesePanel;
import panels.AdminCheesesListPanel;
import panels.EditCheesePanel;
import panels.PageHeadPanel;
import war.CheesePage;

public class CheesesView extends CheesePage {

    public CheesesView() {
        add(new PageHeadPanel("head"));

        AdminCheesesListPanel adminCheesesListPanel =
                new AdminCheesesListPanel("cheesesListPanel");
        adminCheesesListPanel.setOutputMarkupId(true);
        add(adminCheesesListPanel);

        AddCheesePanel addCheesePanel = new AddCheesePanel("addCheesePanel");
        addCheesePanel.setOutputMarkupId(true);
        addCheesePanel.setOutputMarkupPlaceholderTag(true);
        addCheesePanel.setVisible(false);
        add(addCheesePanel);

        EditCheesePanel editCheesePanel =
                new EditCheesePanel("editCheesePanel");
        editCheesePanel.setOutputMarkupId(true);
        editCheesePanel.setOutputMarkupPlaceholderTag(true);
        editCheesePanel.setVisible(false);
        add(editCheesePanel);
    }
}
