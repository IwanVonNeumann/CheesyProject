package dao.hiber;

import dao.iface.*;
import org.hibernate.SessionFactory;

/**
 * Created by Iwan on 14.15.3
 */
public class HiberConnection implements DBConnection {

    private SessionFactory sessionFactory;

    public HiberConnection(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public AddressDAO getAddressDAO() {
        return new HiberAddressDAO(sessionFactory);
    }

    @Override
    public CartDAO getCartDAO() {
        return new HiberCartDAO(sessionFactory);
    }

    @Override
    public CartEntryDAO getCartEntryDAO() {
        return new HiberCartEntryDAO(sessionFactory);
    }

    @Override
    public CheeseDAO getCheeseDAO() {
        return new HiberCheeseDAO(sessionFactory);
    }

    @Override
    public CommentDAO getCommentDAO() {
        return null; // TODO
    }

    @Override
    public LikeDAO getLikeDAO() {
        return null; // TODO
    }
}