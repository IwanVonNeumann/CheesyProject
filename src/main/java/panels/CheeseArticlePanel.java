package panels;

import domain.Cart;
import domain.Cheese;
import look.CurrencyLabel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import views.StoreView;

/**
 * Created by IRuskevich on 09.05.2014
 */
public class CheeseArticlePanel extends CheesePanel {

    private FeedbackPanel feedbackPanel;
    private Label commentsCount;

    public CheeseArticlePanel(String id, final IModel model) {
        super(id, model);

        add(new Label("name"));
        add(new Label("description"));
        add(new CurrencyLabel("price"));

        add(new AjaxFallbackLink("add", model) {
            @Override
            public void onClick(AjaxRequestTarget target) {
                getCart().addCheese(
                        (Cheese) getModelObject());

                if (target != null) {
                    target.addComponent(getShoppingCartPanel());
                }
            }
        });

        feedbackPanel = new FeedbackPanel("feedback", getModel());
        feedbackPanel.setOutputMarkupId(true);
        feedbackPanel.setOutputMarkupPlaceholderTag(true);
        feedbackPanel.setVisible(false);

        add(feedbackPanel);

        AjaxFallbackLink commentsLink = new AjaxFallbackLink("comments") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                feedbackPanel.setVisible(
                        !feedbackPanel.isVisible());
                if (target != null) {
                    target.addComponent(feedbackPanel);
                }
            }
        };

        add(commentsLink);

        Cheese cheese = (Cheese)getModelObject();
        commentsCount = new Label("commentsCount",
                new PropertyModel(cheese, "comments.size"));
        commentsCount.setOutputMarkupId(true);
        commentsCount.setOutputMarkupPlaceholderTag(true);

        commentsLink.add(commentsCount);
    }


    public Label getCommentsCountLabel() {
        return commentsCount;
    }

    private Cart getCart() {
        return getCheeseSession().getCart();
    }

    private CheesesListPanel getCheesesListPanel() {
        return (CheesesListPanel) getParent().getParent().getParent();
    }

    private StoreView getStoreView() {
        return getCheesesListPanel().getStoreView();
    }

    private ShoppingCartPanel getShoppingCartPanel() {
        return getStoreView().getShoppingCartPanel();
    }
}