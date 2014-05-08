package panels;

import domain.Cart;
import domain.Cheese;
import look.CurrencyLabel;
import models.CheesesLDModel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.CompoundPropertyModel;

import java.util.List;

public class CheesesListPanel extends CheesePanel {

    public CheesesListPanel(String id) {
        super(id);

        CheesesLDModel cheesesModel = new CheesesLDModel(
                getCheeseSession().getDataCache());

        PageableListView cheeses =
                new PageableListView("cheeses", cheesesModel, 3) {
                    @Override
                    protected void populateItem(ListItem listItem) {
                        listItem.setModel(
                                new CompoundPropertyModel(
                                        listItem.getModel())
                        );

                        listItem.add(new Label("name"));
                        listItem.add(new Label("description"));
                        listItem.add(new CurrencyLabel("price"));

                        listItem.add(new AjaxFallbackLink("add", listItem.getModel()) {
                            @Override
                            public void onClick(AjaxRequestTarget target) {
                                getCart().addCheese(
                                        (Cheese) getModelObject());
                                //class org.apache.wicket.markup.html.list.ListItem
                                //class panels.CheesesListPanel$1
                                //class panels.CheesesListPanel
                                //class war.Index
                                if (target != null) {
                                    target.addComponent(getParent().getParent().getParent().
                                            getParent().get("shoppingCart"));
                                }
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