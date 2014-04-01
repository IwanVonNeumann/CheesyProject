package panels;

import domain.Cart;
import domain.Cheese;

import models.CheesesModel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.CompoundPropertyModel;

import java.util.List;


public class CheesesListPanel extends CheesePanel {

    public CheesesListPanel(String id) {
        super(id);

        CheesesModel cheesesModel = new CheesesModel(
                getCheeseSession().getDataCache());

        PageableListView cheeses =
                new PageableListView("cheeses", cheesesModel, 3) {
                    @Override
                    protected void populateItem(ListItem listItem) {
                        listItem.setModel(
                                new CompoundPropertyModel(
                                        listItem.getModel()));

                        listItem.add(new Label("name"));
                        listItem.add(new Label("description"));
                        listItem.add(new Label("price"));
                                //"$" + cheese.getPrice().toString()));

                        listItem.add(new Link("add", listItem.getModel()) {
                            //listItem.add(new AjaxFallbackLink("add", listItem.getModel()) {
                            @Override
                            public void onClick() { //AjaxRequestTarget target) {
                                getCart().addCheese(
                                        (Cheese)getModelObject());
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

    protected Cart getCart() {
        return getCheeseSession().getCart();
    }

    protected List<Cheese> getCheeses() {
        return getCheeseSession().getDataCache().getCheesesList();
    }
}