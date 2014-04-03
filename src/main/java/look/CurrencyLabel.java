package look;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

/**
 * Created by Iwan on 14.30.3
 */
public class CurrencyLabel  extends Label{

    public CurrencyLabel(String id) {
        super(id);
    }

    /*public CurrencyLabel(String id, String label) {
        super(id, label);
    }*/

    public CurrencyLabel(String id, IModel model) {
        super(id, model);
    }

    @Override
    protected void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
        String formatted = String.format("$%.2f", (Double)getModelObject());
        replaceComponentTagBody(markupStream, openTag, formatted);
    }
}
