package panels;

import domain.Cheese;
import look.CurrencyLabel;
import look.RowModifier;
import models.CheesesModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.CompoundPropertyModel;
import views.CheesesView;

public class AdminCheesesListPanel extends CheesePanel {

    public AdminCheesesListPanel(String id) {
        super(id);

        CheesesModel cheesesModel = new CheesesModel(
                getCheeseSession().getDataCache());

        PageableListView cheeses =
                new PageableListView("cheeses", cheesesModel, 10) {
                    @Override
                    protected void populateItem(ListItem listItem) {
                        listItem.setModel(
                                new CompoundPropertyModel(
                                        listItem.getModel()));

                        final int i = listItem.getIndex() + 1;
                        listItem.add(new RowModifier(i));
                        listItem.add(new Label("num", String.valueOf(i)));
                        listItem.add(new Label("name"));
                        listItem.add(new Label("description"));
                        listItem.add(new CurrencyLabel("price"));

                        listItem.add(new Link("edit", listItem.getModel()) {
                            @Override
                            public void onClick() {
                                //System.out.println("Edit Cheese clicked...");
                                Cheese selected = (Cheese) getModelObject();
                                setResponsePage(new CheesesView(selected));
                            }
                        });

                        listItem.add(new Link("delete", listItem.getModel()) {
                            @Override
                            public void onClick() {
                                //System.out.println("Deleting Cheese clicked...");
                                Cheese selected = (Cheese) getModelObject();
                                getCheeseSession().getDataCache().
                                        safeDeleteCheese(selected);
                                setResponsePage(CheesesView.class);
                            }
                        });
                    }

                    /*
                    // переделывай wi:157
                    @Override
                    protected IModel getListItemModel(IModel listViewModel, int index) {
                        Cheese cheese = ((List<Cheese>)listViewModel).get(index);
                        // ! return new CompoundPropertyModel(new CheeseModel(cheese));
                        return null; // !!!
                    }*/
                };

        add(cheeses);

        add(new PagingNavigator("navigator", cheeses));
    }

    /*protected List<Cheese> getEntries() {
        return getCheeseSession().getDataCache().getCheesesList();
    }*/
}
