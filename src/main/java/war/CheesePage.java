package war;

import domain.Cart;
import org.apache.wicket.markup.html.WebPage;

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
