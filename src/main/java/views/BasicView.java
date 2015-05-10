package views;

import panels.component.PageHeadPanel;
import war.CheesePage;

/**
 * Created by IRuskevich on 15.05.2014
 */

public abstract class BasicView extends CheesePage {

    private PageHeadPanel pageHeadPanel;


    protected BasicView() {
        pageHeadPanel = new PageHeadPanel("head");
        add(pageHeadPanel);
    }


    protected PageHeadPanel getPageHeadPanel() {
        return pageHeadPanel;
    }
}
