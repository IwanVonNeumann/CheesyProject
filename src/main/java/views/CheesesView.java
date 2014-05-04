package views;

import panels.AdminCheesesListPanel;
import panels.CheeseDataPanel;
import panels.PageHeadPanel;
import war.CheesePage;

public class CheesesView extends CheesePage {

    private AdminCheesesListPanel adminCheesesListPanel;
    private CheeseDataPanel cheeseDataPanel;

    public CheesesView() {
        add(new PageHeadPanel("head"));

        adminCheesesListPanel = new AdminCheesesListPanel("cheesesListPanel");
        adminCheesesListPanel.setOutputMarkupId(true);
        add(adminCheesesListPanel);

        cheeseDataPanel = new CheeseDataPanel("cheeseDataPanel");
        cheeseDataPanel.setOutputMarkupId(true);
        cheeseDataPanel.setOutputMarkupPlaceholderTag(true);
        add(cheeseDataPanel);
    }

    public AdminCheesesListPanel getAdminCheesesListPanel() {
        return adminCheesesListPanel;
    }

    public CheeseDataPanel getCheeseDataPanel() {
        return cheeseDataPanel;
    }
}