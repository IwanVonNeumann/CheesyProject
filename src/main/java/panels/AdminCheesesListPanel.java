package panels;

import domain.Cheese;
import look.RowModifier;
import models.CheesesModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import views.ViewCheeses;

public class AdminCheesesListPanel extends CheesePanel {

    public AdminCheesesListPanel(String id) {
        super(id);

        CheesesModel cheesesModel = new CheesesModel(
                getCheeseSession().getDataCache());

        PageableListView cheeses =
                new PageableListView("cheeses", cheesesModel, 10) {
                    @Override
                    protected void populateItem(ListItem listItem) {
                        Cheese cheese = (Cheese) listItem.getModelObject();
                        final int i = listItem.getIndex() + 1;
                        listItem.add(new RowModifier(i));
                        listItem.add(new Label("num", String.valueOf(i)));
                        listItem.add(new Label("name", cheese.getName()));
                        listItem.add(new Label("description",
                                cheese.getDescription()));
                        listItem.add(new Label("price",
                                "$" + cheese.getPrice().toString()));

                        listItem.add(new Link("edit", listItem.getModel()) {
                            @Override
                            public void onClick() {
                                //System.out.println("Edit Cheese clicked...");
                                Cheese selected = (Cheese) getModelObject();
                                setResponsePage(new ViewCheeses(selected));
                            }
                        });

                        listItem.add(new Link("delete", listItem.getModel()) {
                            @Override
                            public void onClick() {
                                //System.out.println("Deleting Cheese clicked...");
                                Cheese selected = (Cheese) getModelObject();

                                //удаление из базы
                                getCheeseSession().getDataCache().
                                        safeDeleteCheese(selected);

                                // удаление из корзины
                                // дописать, чтобы работало для всех сессий
                                /*getCheeseSession().getCart().removeCheese(
                                        new MultiCheese(selected));*/
                                getCheeseSession().getCart().removeCheese(selected);

                                setResponsePage(ViewCheeses.class);
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

    /*protected List<Cheese> getCheeses() {
        return getCheeseSession().getDataCache().getCheesesList();
    }*/
}
