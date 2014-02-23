package war;

import domain.Cart;
import domain.Cheese;
import org.apache.wicket.markup.html.WebPage;

import java.util.List;

public abstract class CheesePage extends WebPage {

    public CheeseSession getCheeseSession() {
        return (CheeseSession)getSession();
    }

    public Cart getCart() {
        return getCheeseSession().getCart();
    }

    /*
    public List<Cheese> getCheeses() {
        return getCheeseSession().getCheeseDAO().getCheesesList();
    } */
}
