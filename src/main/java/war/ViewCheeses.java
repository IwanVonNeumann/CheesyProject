package war;

import domain.Cheese;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;

public class ViewCheeses extends CheesePage {

    public ViewCheeses() {
        this(new Cheese());
    }

    public ViewCheeses(Cheese cheese) {
        add(new PageHeadPanel("head"));
        add(new AdminCheesesListPanel("cheesesListPanel"));
        add(new AddCheesePanel("addCheesePanel"));
        add(new EditCheesePanel("editCheesePanel", cheese));
    }
}
