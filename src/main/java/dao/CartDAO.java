package dao;

import domain.Address;
import domain.Cart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO extends DAO {

    public CartDAO(Connection connection) {
        super(connection);
        System.out.println("Creating Cart DAO...");
    }

    public List<Cart> getCartsList() {
        List<Cart> list = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            //System.out.println("Accessing data...");
            statement = connection.createStatement();
            result = statement.executeQuery(
                    "SELECT * FROM Carts;");
            System.out.println("[JDBC] SELECT * FROM Carts");
            list = new ArrayList<Cart>();
            while (result.next()) {
                Cart cart = new Cart(result.getTimestamp("Clock"),
                        result.getInt("CartID"),
                        result.getInt("CustomerID")
                );
                list.add(cart);
            }
        } catch (SQLException e) {
            System.out.println("Exception while accessing data...");
        } finally {
            closeResultSet(result);
            closeStatement(statement);
            return list;
        }
    }

    public void insertCart(Address address, Cart cart) {
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;

        try {
            statement = connection.prepareStatement(
                    "INSERT INTO Carts (Clock, CustomerID) " +
                            "VALUES (?, ?);");
            statement.setTimestamp(1, cart.getTime());
            statement.setInt(2, address.getId());
            statement.executeUpdate();

            generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            cart.setId(generatedKeys.getInt(1));

            System.out.println("[JDBC] INSERT INTO Carts " +
                    "(Clock, CustomerID)\n" +
                    "\t\tVALUES (date, \"" + address.getId() + "\");");
        } catch (SQLException e) {
            System.out.println("Exception while inserting data...");
        } finally {
            closeStatement(statement);
            closeResultSet(generatedKeys);
        }
    }
}
