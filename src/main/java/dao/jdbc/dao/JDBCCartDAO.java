package dao.jdbc.dao;

import dao.iface.AddressDAO;
import dao.iface.CartDAO;
import dao.iface.CartEntryDAO;
import dao.jdbc.mappers.CartRowMapper;
import domain.Address;
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

public class JDBCCartDAO extends JDBCDAO implements CartDAO {

    private AddressDAO addressDAO;
    private CartEntryDAO cartEntryDAO;

    public JDBCCartDAO() {
        super();
        System.out.println("[JDBC] Creating Cart DAO...");
    }

    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    public void setCartEntryDAO(CartEntryDAO cartEntryDAO) {
        this.cartEntryDAO = cartEntryDAO;
    }


    @Override
    public List<Cart> getCartsList() {

        System.out.println("[JDBC] SELECT * FROM Carts;");

        String query = "SELECT * FROM Carts";

        return jdbcTemplate.query(query, new CartRowMapper(addressDAO, cartEntryDAO));
    }

    @Override
    public List<Cart> getCartsList(Address address) {

        System.out.println("[JDBC] SELECT * FROM Carts\n" +
                "\tWHERE CustomerID = " + address.getId() + ";");

        String query = "SELECT * FROM Carts WHERE CustomerID = ?";

        return jdbcTemplate.query(query, new Object[]{address.getId()},
                new CartRowMapper(addressDAO, cartEntryDAO));
    }

    @Override
    public void insertCart(final Cart cart) {

        System.out.println("[JDBC] INSERT INTO Carts (Clock, CustomerID)\n" +
                "\t\tVALUES (date, " + cart.getAddress().getId() + ");");

        final String query = "INSERT INTO Carts (Clock, CustomerID) VALUES (?, ?);";

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                statement.setTimestamp(1, cart.getTime()); // База отбрасывает милисекунды
                statement.setLong(2, cart.getAddress().getId());
                return statement;
            }
        }, holder);

        cart.setId(holder.getKey().longValue());
        System.out.println("[JDBC] ID granted: " + cart.getId());

        for (CartEntry item : cart.getEntries()) {
            cartEntryDAO.insertCartEntry(cart, item);
        }
    }
}
