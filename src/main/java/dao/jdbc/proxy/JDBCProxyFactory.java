package dao.jdbc.proxy;

import dao.iface.CartDAO;
import dao.iface.CommentDAO;
import dao.iface.LikeDAO;
import dao.iface.ProxyFactory;
import domain.Address;
import domain.Cheese;
import domain.Title;

/**
 * Created by Iwan on 12.10.2014
 */

public class JDBCProxyFactory implements ProxyFactory {

    private CartDAO cartDAO;
    private CommentDAO commentDAO;
    private LikeDAO likeDAO;

    public JDBCProxyFactory() {
    }

    public void setCartDAO(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    public void setCommentDAO(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public void setLikeDAO(LikeDAO likeDAO) {
        this.likeDAO = likeDAO;
    }

    @Override
    public Address getAddressProxy(Title title, String name, String street, String city,
                                   Integer zipCode, int id, byte[] hash, boolean deleted) {
        return new JDBCAddressProxy(title, name, street, city, zipCode, id, hash, deleted, cartDAO);
    }

    @Override
    public Cheese getCheeseProxy(int id, String name, String description, Double price, boolean deleted) {
        return new JDBCCheeseProxy(id, name, description, price, deleted, commentDAO, likeDAO);
    }
}
