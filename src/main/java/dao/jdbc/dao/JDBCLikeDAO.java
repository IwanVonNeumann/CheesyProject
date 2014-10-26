package dao.jdbc.dao;

import dao.iface.LikeDAO;
import dao.jdbc.mappers.AddressRowMapper;
import domain.Address;
import domain.Cheese;
import domain.Like;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by IRuskevich on 10.05.2014
 */

public class JDBCLikeDAO extends JDBCDAO implements LikeDAO {

    public JDBCLikeDAO() {
        super();
        System.out.println("[JDBC] Creating Like DAO...");
    }

    @Override
    public List<Address> getLikesList(Cheese cheese) {

        System.out.println("[JDBC] SELECT " +
                "Customers.CustomerID as CustomerID, CustomerName, " +
                "Street, ZipCode, City, PasswordHash, Deleted, Title\n\t" +
                "FROM Likes INNER JOIN Customers on Likes.CustomerID = Customers.CustomerID\n\t" +
                "WHERE CheeseID = " + cheese.getId() + ";");

        String query = "SELECT " +
                "Customers.CustomerID as CustomerID, CustomerName, " +
                "Street, ZipCode, City, PasswordHash, Deleted, Title " +
                "FROM Likes INNER JOIN Customers on Likes.CustomerID = Customers.CustomerID " +
                "WHERE CheeseID = ?";

        return jdbcTemplate.query(query, new Object[]{cheese.getId()}, new AddressRowMapper());
    }

    @Override
    public void insertLike(final Like like) {
        if (exists(like)) return;

        System.out.println("[JDBC] INSERT INTO Likes\n" +
                "\t(CheeseID, CustomerID)\n" +
                "\tVALUES (" + like.getCheese().getId() + ", " + like.getAddress().getId() + ");");

        final String query = "INSERT INTO Likes " +
                "(CheeseID, CustomerID) " +
                "VALUES (?, ?);";

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                statement.setLong(1, like.getCheese().getId());
                statement.setLong(2, like.getAddress().getId());
                return statement;
            }
        }, holder);

//        like.setId(holder.getKey().longValue());
//        System.out.println("[JDBC] ID granted: " + like.getId());
    }

    @Override
    public boolean exists(Like like) {

        System.out.println("[JDBC] SELECT COUNT(LikeID) FROM Cheeses\n" +
                "\tWHERE CheeseID = " + like.getCheese().getId() +
                " AND CustomerID = " + like.getAddress().getId() + ";");

        String query = "SELECT COUNT(LikeID) FROM Likes WHERE CheeseID = ? AND CustomerID = ?";

        int count = jdbcTemplate.queryForObject(query,
                new Object[]{like.getCheese().getId(), like.getAddress().getId()}, Integer.class);

        return count > 0;
    }
}
