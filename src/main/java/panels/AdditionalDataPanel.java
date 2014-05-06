package panels;

import look.HashLabel;
import org.apache.wicket.model.IModel;

/**
 * Created by IRuskevich on 06.05.2014
 */
public class AdditionalDataPanel extends CheesePanel {

    public AdditionalDataPanel(String id, IModel model) {
        super(id, model);

        add(new HashLabel("hash", 4));
    }
}
