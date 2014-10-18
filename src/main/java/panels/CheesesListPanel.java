package panels;

import domain.Cart;
import domain.Cheese;
import models.CheesesLDModel;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.CompoundPropertyModel;
import views.StoreView;
import war.CheeseApplication;

import java.util.List;

public class CheesesListPanel extends CheesePanel {

    public CheesesListPanel(String id) {
        super(id);

        CheesesLDModel cheesesModel =
                (CheesesLDModel) CheeseApplication.get().getModelLoader().getCheesesModel();

        PageableListView cheeses =
                new PageableListView("cheeses", cheesesModel, 3) {
                    @Override
                    protected void populateItem(ListItem listItem) {
                        listItem.add(new CheeseArticlePanel("cheese",
                                new CompoundPropertyModel(listItem.getModel())));
                    }
                };
        add(cheeses);
        add(new PagingNavigator("navigator", cheeses));
    }

    public StoreView getStoreView() {
        return (StoreView)getParent();
    }

    protected Cart getCart() {
        return getCheeseSession().getCart();
    }

    protected List<Cheese> getCheeses() {
        return getCheeseSession().getDataCache().getCheesesList();
    }
}