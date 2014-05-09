package dao.hiber;

import dao.iface.CommentDAO;
import domain.Address;
import domain.Cheese;
import domain.Comment;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by IRuskevich on 09.05.2014
 */
public class HiberCommentDAO extends HiberDAO implements CommentDAO {

    public HiberCommentDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Comment> getCommentsList(Cheese cheese) {
        return null;
    }

    @Override
    public void insertComment(Comment comment, Cheese cheese, Address address) {

    }
}
