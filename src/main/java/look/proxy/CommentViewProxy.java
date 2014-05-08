package look.proxy;

import domain.Comment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by IRuskevich on 08.05.2014
 */
public class CommentViewProxy {

    private Comment comment;

    public CommentViewProxy(Comment comment) {
        this.comment = comment;
    }

    public Comment getComment() {
        return comment;
    }


    public String getAuthor() {
        return comment.getAddress().getName();
    }

    public String getText() {
        return comment.getText();
    }

    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        return dateFormat.format(comment.getTime());
    }

    public String getTime() {
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return timeFormat.format(comment.getTime());
    }
}