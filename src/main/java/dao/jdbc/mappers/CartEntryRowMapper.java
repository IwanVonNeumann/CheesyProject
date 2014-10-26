package dao.jdbc.mappers;

import dao.iface.CheeseDAO;
import domain.CartEntry;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Iwan on 26.10.2014
 */

public class CartEntryRowMapper implements RowMapper<CartEntry> {

    private CheeseDAO cheeseDAO;

    public CartEntryRowMapper(CheeseDAO cheeseDAO) {
        this.cheeseDAO = cheeseDAO;
    }

    @Override
    public CartEntry mapRow(ResultSet resultSet, int i) throws SQLException {
        CartEntry cartEntry = new CartEntry();
        cartEntry.setId(resultSet.getLong("EntryID"));
        cartEntry.setQuantity(resultSet.getInt("Quantity"));
        cartEntry.setCheese(cheeseDAO.getCheese(resultSet.getLong("CheeseID")));
        return cartEntry;
    }
}
