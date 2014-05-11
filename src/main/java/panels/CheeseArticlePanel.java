package panels;

import domain.Address;
import domain.Cart;
import domain.Cheese;
import look.CurrencyLabel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import views.StoreView;

/**
 * Created by IRuskevich on 09.05.2014
 */
public class CheeseArticlePanel extends CheesePanel {

    private Cheese cheese;

    private FeedbackPanel feedbackPanel;
    private Label likesCount;
    private Label commentsCount;

    public CheeseArticlePanel(String id, final IModel model) {
        super(id, model);

        cheese = (Cheese)getModelObject();

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
                    TextField commentField = feedbackPanel.getCommentField();
                    target.focusComponent(commentField);
                }
            }
        };

        add(commentsLink);

        commentsCount = new Label("commentsCount",
                new PropertyModel(cheese, "comments.size"));
        commentsCount.setOutputMarkupId(true);

        commentsLink.add(commentsCount);

        AjaxFallbackLink likeLink = new AjaxFallbackLink("like") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                Address address = getCheeseSession().getAddress();
                if (cheese.like(address)) {
                    getCheeseSession().getDataCache().insertLike(cheese, address);

                    if (target != null) {
                        target.addComponent(likesCount);
                    }
                }
            }
        };

        add(likeLink);

        likesCount= new Label("likesCount",
                new PropertyModel(cheese, "likes.size"));
        likesCount.setOutputMarkupId(true);

        likeLink.add(likesCount);
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