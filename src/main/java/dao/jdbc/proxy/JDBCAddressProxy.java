package dao.jdbc.proxy;

import dao.iface.CartDAO;
import dao.jdbc.dao.JDBCCartDAO;
import domain.Address;
import domain.Cart;

import java.util.List;

/**
 * Created by IRuskevich on 14.18.3.
 */
public class JDBCAddressProxy extends Address {

    // TODO: инициализировать грамотно!
    private CartDAO cartDAO;

    public JDBCAddressProxy() {
    }

    // базовый конструктор
    public JDBCAddressProxy(String name, String street, String city, Integer zipCode) {
        super(name, street, city, zipCode);
    }

    // регистрация
    public JDBCAddressProxy(String name, String street, String city, Integer zipCode, String password) {
        super(name, street, city, zipCode, password);
    }

    // считывание из базы, запись создана скриптом
    public JDBCAddressProxy(String name, String street, String city, Integer zipCode, int id) {
        super(name, street, city, zipCode, id);
    }

    // считывание из базы, запись создана программно
    public JDBCAddressProxy(String name, String street, String city,
                            Integer zipCode, int id, byte[] hash, boolean deleted) {
        super(name, street, city, zipCode, id, hash, deleted);
    }

    @Override
    public List<Cart> getPurchases() {
        System.out.println("Address.getPurchases(): lazy initialization...");
        List<Cart> purchases = super.getPurchases();
        if (purchases == null) {
            purchases = cartDAO.getCartsList(this);
        }
        return purchases;
    }
}
