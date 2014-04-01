package panels;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import war.CheeseSession;

/**
 * Created by IRuskevich on 14.24.2
 */
public abstract class CheesePanel extends Panel{

    protected CheesePanel(String id) {
        super(id);
    }

    protected CheesePanel(String id, IModel model) {
        super(id, model);
    }

    protected CheeseSession getCheeseSession() {
        return (CheeseSession) getSession();
    }
}
