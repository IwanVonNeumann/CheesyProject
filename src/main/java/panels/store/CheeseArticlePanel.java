package panels.store;

import domain.Address;
import domain.Cart;
import domain.Cheese;
import domain.Like;
import look.CurrencyLabel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import panels.CheesePanel;
import panels.component.ShoppingCartPanel;
import views.StoreView;

/**
 * Created by IRuskevich on 09.05.2014
 */

// TODO get list of likes
public class CheeseArticlePanel extends CheesePanel {

    private Cheese cheese;

    private Label likesCount;
    private Label commentsCount;

    public CheeseArticlePanel(String id, final IModel model) {
        super(id, model);

        cheese = (Cheese) getModelObject();
        String feedbackPanelId = "feedback-" + cheese.getId();

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

        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback", getModel());
        feedbackPanel.add(new SimpleAttributeModifier("id", feedbackPanelId));
        add(feedbackPanel);

        AjaxFallbackLink commentsLink = new AjaxFallbackLink("comments") {
            @Override
            public void onClick(AjaxRequestTarget target) {
            }
        };
        commentsLink.add(new SimpleAttributeModifier("data-target", "#" + feedbackPanelId));


        commentsCount = new Label("commentsCount",
                new PropertyModel(cheese, "comments.size"));


        add(commentsLink);
        add(commentsCount);

        AjaxFallbackLink likeLink = new AjaxFallbackLink("like") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                Address address = getCheeseSession().getAddress();
                if (cheese.like(address)) {
                    getCheeseSession().getDataCache().insertLike(new Like(cheese, address));

                    if (target != null) {
                        target.addComponent(likesCount);
                    }
                }
            }

            @Override
            public boolean isEnabled() {
                boolean alreadyLiked = getCheeseSession().getDataCache().exists(
                        new Like(cheese, getAddress()));
                return !alreadyLiked;
            }
        };

        likesCount = new Label("likesCount",
                new PropertyModel(cheese, "likes.size"));
        likesCount.setOutputMarkupId(true);

        add(likeLink);
        add(likesCount);
    }


    public Label getCommentsCountLabel() {
        return commentsCount;
    }

    private Address getAddress() {
        return getCheeseSession().getAddress();
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
