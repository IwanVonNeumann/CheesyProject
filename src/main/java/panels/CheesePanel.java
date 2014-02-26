package panels;

import org.apache.wicket.markup.html.panel.Panel;
import war.CheeseSession;

/**
 * Created by IRuskevich on 14.24.2.
 */
public abstract class CheesePanel extends Panel{

    protected CheesePanel(String id) {
        super(id);
    }

    protected CheeseSession getCheeseSession() {
        return (CheeseSession) getSession();
    }
}