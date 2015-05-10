package views;

import panels.AdminCheesesListPanel;
import panels.CheeseDataPanel;

public class CheesesView extends BasicView {

    private AdminCheesesListPanel adminCheesesListPanel;
    private CheeseDataPanel cheeseDataPanel;

    public CheesesView() {

        getPageHeadPanel().setActiveLink("cheeses");

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