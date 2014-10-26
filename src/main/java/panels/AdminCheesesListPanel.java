package panels;

import domain.Cheese;

import look.CurrencyLabel;
import look.RowModifier;

import models.CheesesLDModel;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.CompoundPropertyModel;

import views.CheesesView;
import war.CheeseApplication;

public class AdminCheesesListPanel extends CheesePanel {

    public AdminCheesesListPanel(String id) {
        super(id);

        final CheesesLDModel cheesesModel =
                (CheesesLDModel) CheeseApplication.get().getModelLoader().getCheesesModel();

        final PageableListView cheeses =
                new PageableListView("cheeses", cheesesModel, 10) {
                    @Override
                    protected void populateItem(final ListItem listItem) {
                        listItem.setModel(
                                new CompoundPropertyModel(
                                        listItem.getModel())
                        );

                        final int i = listItem.getIndex() + 1;
                        listItem.add(new RowModifier(i));
                        listItem.add(new Label("num", String.valueOf(i)));
                        listItem.add(new Label("name"));
                        listItem.add(new Label("description"));
                        listItem.add(new CurrencyLabel("price"));

                        listItem.add(new AjaxFallbackLink("edit", listItem.getModel()) {
                            @Override
                            public void onClick(AjaxRequestTarget target) {
                                CheeseDataPanel cheeseDataPanel =
                                        getCheesesView().getCheeseDataPanel();
                                cheeseDataPanel.setFormModel(getModel());
                                cheeseDataPanel.showEditForm(target);
                            }
                        });

                        listItem.add(new AjaxFallbackLink("delete", listItem.getModel()) {
                            @Override
                            public void onClick(AjaxRequestTarget target) {
                                System.out.println("Deleting Cheese clicked...");
                                Cheese selected = (Cheese) getModelObject();
                                getCheeseSession().getDataCache().
                                        safeDeleteCheese(selected);

                                // TODO: все еще не работает

                                // TODO:
                                // не работает
                                // вероятно, из-за модели:
                                // Deleting Cheese clicked...
                                // [Wicket] CheesesLDModel.load();
                                // [JDBC] SELECT * FROM Cheeses
                                // WHERE Deleted <> true;
                                // [Wicket] CheesesLDModel.onAttach();
                                // [JDBC] UPDATE Cheeses
                                // SET Deleted = true
                                // WHERE CheeseID = 19
                                //         [Wicket] CheesesLDModel.onDetach();

                                if (target != null) {
                                    AdminCheesesListPanel adminCheesesListPanel =
                                            getCheesesView().getAdminCheesesListPanel();
//                                    System.out.println("AdminCheesesListPanel: " + adminCheesesListPanel.getClass());
                                    target.addComponent(adminCheesesListPanel);
                                }
                            }
                        });
                    }

                    /*
                    // TODO: переделывай wi:157
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

    private CheesesView getCheesesView() {
        return (CheesesView) getParent();
    }
}