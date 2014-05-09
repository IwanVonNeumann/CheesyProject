package cache;

import dao.iface.CommentDAO;
import domain.Address;
import domain.Cheese;
import domain.Comment;

import java.util.List;

/**
 * Created by IRuskevich on 09.05.2014
 */
public class CommentCache implements CommentDAO{

    private CommentDAO commentDAO;

    public CommentCache(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Override
    public List<Comment> getCommentsList(Cheese cheese) {
        return commentDAO.getCommentsList(cheese);
    }

    @Override
    public void insertComment(Comment comment, Cheese cheese, Address address) {
        commentDAO.insertComment(comment, cheese, address);
    }
}