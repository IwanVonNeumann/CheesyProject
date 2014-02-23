package war;

import domain.Cart;
import domain.Cheese;
import models.CheesesModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;

import java.util.List;

public class CheesesListPanel extends Panel {

    private CheesesModel cheesesModel;

    public CheesesListPanel(String id) {
        super(id);

        cheesesModel = new CheesesModel(
                getCheeseSession().getCheeseDAO());

        PageableListView cheeses =
                new PageableListView("cheeses", cheesesModel, 3) {
                    @Override
                    protected void populateItem(ListItem listItem) {
                        Cheese cheese = (Cheese) listItem.getModelObject();
                        listItem.add(new Label("name", cheese.getName()));
                        listItem.add(new Label("description",
                                cheese.getDescription()));
                        listItem.add(new Label("price",
                                "$" + cheese.getPrice().toString()));
                        listItem.add(new Link("add", listItem.getModel()) {
                            //listItem.add(new AjaxFallbackLink("add", listItem.getModel()) {
                            @Override
                            public void onClick() { //AjaxRequestTarget target) {
                                Cheese selected = (Cheese) getModelObject();
                                getCart().addCheese(selected);
                        /*
                        if (target != null) {
                            target.addComponent(shoppingCart);
                        } */
                            }
                        });
                    }
                };
        add(cheeses);
        add(new PagingNavigator("navigator", cheeses));
    }

    protected CheeseSession getCheeseSession() {
        return (CheeseSession)getSession();
    }

    protected Cart getCart() {
        return getCheeseSession().getCart();
    }

    protected List<Cheese> getCheeses() {
        return getCheeseSession().getCheeseDAO().getCheesesList();
        //return CheeseApplication.get().getCheeses();
    }
}
