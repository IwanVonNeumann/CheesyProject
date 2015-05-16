package panels.store;

import domain.Address;
import domain.Cheese;
import domain.Comment;
import look.proxy.CommentViewProxy;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import panels.CheesePanel;

import java.sql.Timestamp;

/**
 * Created by IRuskevich on 08.05.2014
 */
public class FeedbackPanel extends CheesePanel {

    private Cheese cheese;

    private WebMarkupContainer container;

    private Form commentForm;
    private TextField commentField;

    public FeedbackPanel(String id, IModel model) {
        super(id, model);

        cheese = (Cheese) getModelObject();

        String feedbackPanelId = "feedback-" + cheese.getId();

        container = new WebMarkupContainer("container");
        container.setOutputMarkupId(true);

        ListView commentsList = new ListView("comments", cheese.getComments()) {
            @Override
            protected void populateItem(ListItem listItem) {
                Comment comment = (Comment) listItem.getModelObject();
                CommentViewProxy commentViewProxy =
                        new CommentViewProxy(comment);
                listItem.setModel(
                        new CompoundPropertyModel(
                                commentViewProxy)
                );

                listItem.add(new Label("author"));
                listItem.add(new Label("text"));
                listItem.add(new Label("date"));
                listItem.add(new Label("time"));
            }
        };

        container.add(commentsList);

        AjaxFallbackLink hideLink = new AjaxFallbackLink("hide") {
            @Override
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
            }
        };
        hideLink.add(new SimpleAttributeModifier("data-target", "#" + feedbackPanelId));
        container.add(hideLink);

        commentForm = new Form("form",
                new CompoundPropertyModel(new Comment()));

        commentField = new TextField("text");
        commentField.setRequired(true)
                .setOutputMarkupId(true);
        commentForm.add(commentField);

        commentForm.add(new AjaxSubmitLink("send") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form form) {
                Comment newComment = (Comment) commentForm.getModelObject();
                newComment.setAddress(getCheeseSession().getAddress());
                newComment.setTime(new Timestamp(System.currentTimeMillis()));
                cheese.comment(newComment);

                Address address = getCheeseSession().getAddress();
                getCheeseSession().getDataCache().insertComment(newComment, cheese, address);

                form.setModel(new CompoundPropertyModel(new Comment()));

                if (target != null) {
                    target.addComponent(container);
                    target.addComponent(getCommentsCountLabel());
                    target.focusComponent(commentField);
                }
            }
        });

        container.add(commentForm);

        add(container);
    }

    private CheeseArticlePanel getCheeseArticlePanel() {
        return (CheeseArticlePanel) getParent();
    }

    private Label getCommentsCountLabel() {
        return getCheeseArticlePanel().getCommentsCountLabel();
    }
}