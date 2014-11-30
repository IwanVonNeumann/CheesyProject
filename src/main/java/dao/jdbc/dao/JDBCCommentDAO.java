package dao.jdbc.dao;

import dao.iface.AddressDAO;
import dao.iface.CommentDAO;
import dao.jdbc.mappers.CommentRowMapper;
import domain.Address;
import domain.Cheese;
import domain.Comment;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by IRuskevich on 08.05.2014
 */

public class JDBCCommentDAO extends JDBCDAO implements CommentDAO {

    private AddressDAO addressDAO;

    public JDBCCommentDAO() {
        super();
        System.out.println("[JDBC] Creating Comment DAO...");
    }

    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    @Override
    public List<Comment> getCommentsList(Cheese cheese) {

        System.out.println("[JDBC] SELECT * FROM Comments\n\t" +
                "WHERE CheeseID = " + cheese.getId() + ";");

        String query = "SELECT * FROM Comments WHERE CheeseID = ?";

        return jdbcTemplate.query(query, new Object[]{cheese.getId()},
                new CommentRowMapper(addressDAO));
    }

    // TODO: remove Address from signature
    @Override
    public void insertComment(final Comment comment, final Cheese cheese, final Address address) {

        System.out.println("[JDBC] INSERT INTO Comments\n" +
                "\t(CheeseID, CustomerID, Text, Clock)\n" +
                "\tVALUES (" + cheese.getId() + ", " + address.getId() + ", " +
                comment.getText() + ", date);");

        final String query = "INSERT INTO Comments " +
                "(CheeseID, CustomerID, Text, Clock) " +
                "VALUES (?, ?, ?, ?)";

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                statement.setLong(1, cheese.getId());
                statement.setLong(2, address.getId());
                statement.setString(3, comment.getText());
                statement.setTimestamp(4, comment.getTime()); // База отбрасывает милисекунды
                return statement;
            }
        }, holder);

        comment.setId(holder.getKey().longValue());
        System.out.println("[JDBC] ID granted: " + comment.getId());
    }
}
