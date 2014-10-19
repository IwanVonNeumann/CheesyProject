package dao.jdbc.proxy;

import dao.iface.CommentDAO;
import dao.iface.LikeDAO;
import domain.Address;
import domain.Cheese;
import domain.Comment;

import java.util.List;
import java.util.Set;

/**
 * Created by IRuskevich on 08.05.2014
 */

public class JDBCCheeseProxy extends Cheese {

    private CommentDAO commentDAO;
    private LikeDAO likeDAO;


    public JDBCCheeseProxy(Cheese cheese, CommentDAO commentDAO, LikeDAO likeDAO) {
        super(
                cheese.getId(),
                cheese.getName(),
                cheese.getDescription(),
                cheese.getPrice(),
                cheese.isDeleted());
        this.commentDAO = commentDAO;
        this.likeDAO = likeDAO;
    }

    @Override
    public Set<Address> getLikes() {
        System.out.println("[JDBC] Cheese proxy call for " +
                getName() + "...");
        if (super.getLikes() == null) {
            System.out.println("[JDBC] Running lazy init for likes list...");
            super.setLikes(
                    likeDAO.getLikesList(this));
        } else {
            System.out.println("[JDBC] Likes list already in memory...");
        }
        return super.getLikes();
    }

    @Override
    public List<Comment> getComments() {
        System.out.println("[JDBC] Cheese proxy call for " +
                getName() + "...");
        if (super.getComments() == null) {
            System.out.println("[JDBC] Running lazy init for comments list...");
            super.setComments(
                    commentDAO.getCommentsList(this));
        } else {
            System.out.println("[JDBC] Comments list already in memory...");
        }
        return super.getComments();
    }
}
