package look;

import org.apache.wicket.markup.html.form.TextField;

/**
 * Created by IRuskevich on 19.05.2014
 */
public class RequiredTextField extends TextField {
    public RequiredTextField(String id) {
        super(id);
        setRequired(true);
    }

    /*public RequiredTextField(String id, Class type) {
        super(id, type);
        setRequired(true);
    }

    public RequiredTextField(String id, IModel object) {
        super(id, object);
        setRequired(true);
    }

    public RequiredTextField(String id, IModel model, Class type) {
        super(id, model, type);
        setRequired(true);
    }*/
}