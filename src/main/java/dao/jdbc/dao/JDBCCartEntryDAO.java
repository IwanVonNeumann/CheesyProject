package dao.jdbc.dao;

import dao.iface.CartEntryDAO;
import dao.jdbc.mappers.CartEntryRowMapper;
import domain.Cart;
import domain.CartEntry;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JDBCCartEntryDAO extends JDBCDAO implements CartEntryDAO {

    private JDBCCheeseDAO cheeseDAO;

    public JDBCCartEntryDAO() {
        super();
        System.out.println("[JDBC] Creating Purchase DAO...");
    }

    public void setCheeseDAO(JDBCCheeseDAO cheeseDAO) {
        this.cheeseDAO = cheeseDAO;
    }

    @Override
    public List<CartEntry> getCartEntries(long cartId) {

        System.out.println("[JDBC] SELECT * FROM CartEntries " +
                "WHERE CartID = " + cartId + ";");

        String query = "SELECT * FROM CartEntries WHERE CartID = ?";

        return jdbcTemplate.query(query, new Object[]{cartId},
                new CartEntryRowMapper(cheeseDAO));
    }

    @Override
    public void insertCartEntry(final Cart cart, final CartEntry cartEntry) {

        System.out.println("[JDBC] INSERT INTO CartEntries " +
                "(CheeseID, CartID, Quantity) " +
                "VALUES (" + cartEntry.getCheeseId() + ", " +
                cart.getId() + ", " + cartEntry.getQuantity() + ");");

        final String query = "INSERT INTO CartEntries " +
                "(CheeseID, CartID, Quantity) " +
                "VALUES (?, ?, ?);";

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                statement.setLong(1, cartEntry.getCheeseId());
                statement.setLong(2, cart.getId());
                statement.setInt(3, cartEntry.getQuantity());
                return statement;
            }
        }, holder);

        cartEntry.setId(holder.getKey().longValue());
        System.out.println("[JDBC] ID granted: " + cartEntry.getId());
    }
}
