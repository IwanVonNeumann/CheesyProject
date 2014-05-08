package panels;

import domain.Cheese;
import domain.Comment;
import look.proxy.CommentViewProxy;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import java.sql.Timestamp;

/**
 * Created by IRuskevich on 08.05.2014
 */
public class CommentsPanel extends CheesePanel {

    private Cheese cheese;

    private WebMarkupContainer container;
    private ListView commentsList;

    private AjaxFallbackLink showLink;
    private AjaxFallbackLink hideLink;

    private Form commentForm;

    public CommentsPanel(String id, IModel model) {
        super(id, model);

        cheese = (Cheese)getModelObject();

        container = new WebMarkupContainer("container");

        commentsList = new ListView("comments", cheese.getComments()) {
            @Override
            protected void populateItem(ListItem listItem) {
                Comment comment = (Comment)listItem.getModelObject();
                CommentViewProxy commentViewProxy =
                        new CommentViewProxy(comment);
                listItem.setModel(
                        new CompoundPropertyModel(
                                commentViewProxy));

                listItem.add(new Label("author"));
                listItem.add(new Label("text"));
                listItem.add(new Label("date"));
                listItem.add(new Label("time"));
            }
        };

        container.add(commentsList);
        container.setVisible(false);
        container.setOutputMarkupId(true);
        container.setOutputMarkupPlaceholderTag(true);

        showLink = new AjaxFallbackLink("show") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                toggleVisibility(target);
            }
        };

        showLink.setOutputMarkupId(true);
        showLink.setOutputMarkupPlaceholderTag(true);
        add(showLink);

        showLink.add(new Label("count", new PropertyModel(cheese, "comments.size")));

        hideLink = new AjaxFallbackLink("hide") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                toggleVisibility(target);
            }
        };

        hideLink.setVisible(false);
        hideLink.setOutputMarkupId(true);
        hideLink.setOutputMarkupPlaceholderTag(true);
        add(hideLink);

        commentForm = new Form("form",
                new CompoundPropertyModel(new Comment()));
        commentForm.add(new TextField("text").setRequired(true));

        commentForm.add(new AjaxSubmitLink("send") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form form) {
                Comment newComment = (Comment)commentForm.getModelObject();
                newComment.setAddress(getCheeseSession().getAddress());
                newComment.setTime(new Timestamp(System.currentTimeMillis()));
                cheese.comment(newComment);

                form.setModel(new CompoundPropertyModel(new Comment()));

                target.addComponent(container);
            }
        });

        container.add(commentForm);

        add(container);
    }

    private void toggleVisibility(AjaxRequestTarget target) {
        showLink.setVisible(!showLink.isVisible());
        hideLink.setVisible(!hideLink.isVisible());
        container.setVisible(!container.isVisible());

        if (target != null) {
            target.addComponent(showLink);
            target.addComponent(hideLink);
            target.addComponent(container);
        }
    }
}