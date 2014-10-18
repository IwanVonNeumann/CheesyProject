package dao.jdbc.dao;

import dao.iface.AddressDAO;
import dao.iface.CommentDAO;
import domain.Address;
import domain.Cheese;
import domain.Comment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IRuskevich on 08.05.2014
 */

public class JDBCCommentDAO extends JDBCDAO implements CommentDAO{

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
        List<Comment> list;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            //System.out.println("Accessing data...");
            statement = connection.prepareStatement(
                    "SELECT * FROM Comments WHERE CheeseID = ?;");
            statement.setInt(1, cheese.getId());
            result = statement.executeQuery();
            System.out.println("[JDBC] SELECT * FROM Comments\n\t" +
                    "WHERE CheeseID = " + cheese.getId() + ";");
            list = new LinkedList<>();
            while (result.next()) {
                list.add(buildComment(result));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Exception while accessing data...");
            return null;
        } finally {
            closeResultSet(result);
            closeStatement(statement);
        }
    }

    @Override
    public void insertComment(Comment comment, Cheese cheese, Address address) {
        PreparedStatement statement = null;

        try {
            System.out.println("Adding Comment for " +
                    cheese.getName() + "...");
            System.out.println("Cheese ID: " + cheese.getId());
            System.out.println("Cheese name: " + cheese.getName());
            System.out.println("Comment text: " + comment.getText());
            System.out.println("Author: " + address.getName());

            statement = connection.prepareStatement(
                    "INSERT INTO Comments " +
                            "(CommentID, CheeseID, CustomerID, Text, Clock) " +
                            "VALUES (?, ?, ?, ?, ?);");
            statement.setInt(1, comment.getId()); // TODO: пересмотреть необходимость
            statement.setInt(2, cheese.getId());
            statement.setInt(3, address.getId());
            statement.setString(4, comment.getText());
            statement.setTimestamp(5, comment.getTime()); // База отбрасывает милисекунды
            statement.executeUpdate();
            System.out.println("[JDBC] INSERT INTO Comments\n" +
                    "\t(CommentID, CheeseID, CustomerID, Text, Clock)\n" +
                    "\tVALUES (" + comment.getId() + ", " +
                    cheese.getId() + ", " + address.getId() + ", " +
                    comment.getText() + ", date);");
        } catch (SQLException e) {
            System.out.println("Exception while inserting data...");
        } finally {
            closeStatement(statement);
        }
    }

    private Comment buildComment(ResultSet result) throws SQLException {
        int customerId = result.getInt("CustomerID");
        Address address = addressDAO.getAddress(customerId);
        return new Comment(result.getInt("CommentID"),
                result.getString("Text"),
                result.getTimestamp("Clock"),
                address);
    }
}