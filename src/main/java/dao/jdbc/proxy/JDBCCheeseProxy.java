package dao.jdbc.proxy;

import dao.jdbc.dao.JDBCCommentDAO;
import dao.jdbc.dao.JDBCLikeDAO;
import domain.Address;
import domain.Cheese;
import domain.Comment;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

/**
 * Created by IRuskevich on 08.05.2014
 */
public class JDBCCheeseProxy extends Cheese {

    private Connection connection;

    public JDBCCheeseProxy(int id, String name, String description, Double price, boolean deleted,
                           Connection connection) {
        super(id, name, description, price, deleted);
        this.connection = connection;
    }

    @Override
    public Set<Address> getLikes() {
        System.out.println("[JDBC] Cheese proxy call for " +
                getName() + "...");
        if (super.getLikes() == null) {
            System.out.println("[JDBC] Running lazy init for likes list...");
            super.setLikes(
                    new JDBCLikeDAO(connection).getLikesList(this));
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
                    new JDBCCommentDAO(connection).getCommentsList(this));
        } else {
            System.out.println("[JDBC] Comments list already in memory...");
        }
        return super.getComments();
    }
}