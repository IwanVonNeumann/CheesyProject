package dao.jdbc.proxy;

import dao.iface.CartDAO;
import dao.iface.CommentDAO;
import dao.iface.LikeDAO;
import dao.iface.ProxyFactory;
import domain.Address;
import domain.Cheese;

import java.util.ArrayList;
import java.util.List;

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
    public Address getAddressProxy(Address address) {
        return new JDBCAddressProxy(address, cartDAO);
    }

    @Override
    public List<Address> getAddressProxyList(List<Address> addressList) {

        List<Address> proxiesList = new ArrayList<>(addressList.size());

        for (Address address : addressList) {
            proxiesList.add(getAddressProxy(address));
        }

        return proxiesList;
    }

    @Override
    public Cheese getCheeseProxy(Cheese cheese) {
        return new JDBCCheeseProxy(cheese, commentDAO, likeDAO);
    }

    @Override
    public List<Cheese> getCheeseProxyList(List<Cheese> cheesesList) {

        List<Cheese> proxiesList = new ArrayList<>(cheesesList.size());

        for (Cheese cheese : cheesesList) {
            proxiesList.add(getCheeseProxy(cheese));
        }

        return proxiesList;
    }
}
