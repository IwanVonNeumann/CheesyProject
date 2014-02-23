package look;

import org.apache.wicket.behavior.SimpleAttributeModifier;

/**
 * Created by User on 14.15.2.
 */
public class RowModifier extends SimpleAttributeModifier {

    public RowModifier(int i) {
        super("class", (i % 2 == 0 ? "even" : "odd"));
    }
}
