package views;

import panels.PageHeadPanel;
import war.CheesePage;

/**
 * Created by IRuskevich on 15.05.2014
 */
public abstract class BasicView extends CheesePage {

    protected BasicView() {
        add(new PageHeadPanel("head"));
    }
}
