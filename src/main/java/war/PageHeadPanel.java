package war;

import org.apache.wicket.markup.html.panel.Panel;


public class PageHeadPanel extends Panel {
    public PageHeadPanel(String id) {
        super(id);

        add(new MenuPanel("menu"));
    }
}
