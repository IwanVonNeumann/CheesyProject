package dao.iface;

import domain.Address;
import domain.Cheese;
import domain.Comment;

import java.util.List;

/**
 * Created by IRuskevich on 08.05.2014
 */
public interface CommentDAO {

    List<Comment> getCommentsList(Cheese cheese);

    void insertComment(Comment comment, Cheese cheese, Address address);
}
