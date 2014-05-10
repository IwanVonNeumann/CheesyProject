package dao.jdbc;

import dao.iface.*;
import dao.jdbc.dao.*;

import java.sql.Connection;

/**
 * Created by IRuskevich on 14.14.3
 */
public class JDBCConnection implements DBConnection {

    private CheeseDAO cheeseDAO;
    private AddressDAO addressDAO;
    private CartEntryDAO cartEntryDAO;
    private CartDAO cartDAO;
    private CommentDAO commentDAO;
    private LikeDAO likeDAO;

    public JDBCConnection(Connection connection) {
        cheeseDAO = new JDBCCheeseDAO(connection);
        addressDAO = new JDBCAddressDAO(connection);
        cartEntryDAO = new JDBCCartEntryDAO(connection);
        cartDAO = new JDBCCartDAO(connection);
        commentDAO = new JDBCCommentDAO(connection);
        likeDAO = new JDBCLikeDAO(connection);
    }

    @Override
    public AddressDAO getAddressDAO() {
        return addressDAO;
    }

    @Override
    public CartDAO getCartDAO() {
        return cartDAO;
    }

    @Override
    public CartEntryDAO getCartEntryDAO() {
        return cartEntryDAO;
    }

    @Override
    public CheeseDAO getCheeseDAO() {
        return cheeseDAO;
    }

    @Override
    public CommentDAO getCommentDAO() {
        return commentDAO;
    }

    @Override
    public LikeDAO getLikeDAO() {
        return likeDAO;
    }
}
