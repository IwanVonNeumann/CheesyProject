package dao.jdbc.mappers;

import dao.iface.AddressDAO;
import dao.iface.CartEntryDAO;
import domain.Cart;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Iwan on 26.10.2014
 */

public class CartRowMapper implements RowMapper<Cart> {

    private AddressDAO addressDAO;
    private CartEntryDAO cartEntryDAO;

    public CartRowMapper(AddressDAO addressDAO, CartEntryDAO cartEntryDAO) {
        this.addressDAO = addressDAO;
        this.cartEntryDAO = cartEntryDAO;
    }

    @Override
    public Cart mapRow(ResultSet resultSet, int i) throws SQLException {
        Cart cart = new Cart();
        cart.setId(resultSet.getLong("CartID"));
        cart.setTime(resultSet.getTimestamp("CLock"));
        cart.setEntries(cartEntryDAO.getCartEntries(
                resultSet.getLong("CartID")));
        cart.setAddress(addressDAO.getAddress(
                resultSet.getLong("CustomerID")));
        return cart;
    }
}
