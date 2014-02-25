package panels;

import domain.Cheese;
import domain.MultiCheese;
import look.RowModifier;
import models.CheesesModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import views.ViewCheeses;

import java.util.List;

public class AdminCheesesListPanel extends CheesePanel {

    CheesesModel cheesesModel;

    public AdminCheesesListPanel(String id) {
        super(id);

        cheesesModel = new CheesesModel(
                getCheeseSession().getCheeseDAO());

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
                                getCheeseSession().getCheeseDAO().
                                        deleteCheese(selected);

                                // удаление из корзины
                                // дописать, чтобы работало для всех сессий
                                getCheeseSession().getCart().removeCheese(
                                        new MultiCheese(selected));

                                setResponsePage(ViewCheeses.class);
                            }
                        });


                    }

                    /* переделывай wi:157
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

    protected List<Cheese> getCheeses() {
        return getCheeseSession().getCheeseDAO().getCheesesList();
    }

}
