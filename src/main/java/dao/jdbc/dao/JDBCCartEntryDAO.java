package dao.jdbc.dao;

import dao.iface.CartEntryDAO;
import domain.Cart;
import domain.CartEntry;
import domain.Cheese;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCCartEntryDAO extends JDBCDAO implements CartEntryDAO {

    private JDBCCheeseDAO cheeseDAO;

    public JDBCCartEntryDAO(Connection connection) {
        super(connection);
        System.out.println("[JDBC] Creating Purchase DAO...");
        cheeseDAO = new JDBCCheeseDAO(connection); // TODO: нужен ли этот объект?
    }

    /*@Override
    public List<CartEntry> getCartEntries(Cart cart) {
        return getCartEntries(cart.getId());
    }*/

    @Override
    public List<CartEntry> getCartEntries(int cartId) {
        List<CartEntry> list;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            //System.out.println("Searching for CartEntries by Cart ID...");
            statement = connection.prepareStatement(
                    "SELECT * FROM CartEntries " +
                            "WHERE CartID = ?;");
            statement.setInt(1, cartId);
            result = statement.executeQuery();

            System.out.println("[JDBC] SELECT * FROM CartEntries " +
                    "WHERE CartID = " + cartId + ";");

            list = new ArrayList<CartEntry>();
            while (result.next()) {
                list.add(buildMultiCheese(result));
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
    public void insertCartEntry(Cart cart, CartEntry cheese) {
        PreparedStatement statement = null;

        try {
            System.out.println("Adding CartEntry for " +
                    cheese.getName() + "...");
            System.out.println("Cart ID: " + cart.getId());
            System.out.println("Cheese ID: " + cheese.getCheeseId());
            System.out.println("Cheese name: " + cheese.getName());
            System.out.println("Quantity: " + cheese.getQuantity());

            statement = connection.prepareStatement(
                    "INSERT INTO CartEntries " +
                            "(CheeseID, CartID, Quantity) " +
                            "VALUES (?, ?, ?);");
            statement.setInt(1, cheese.getCheeseId());
            statement.setInt(2, cart.getId());
            statement.setInt(3, cheese.getQuantity());
            statement.executeUpdate();
            System.out.println("[JDBC] INSERT INTO CartEntries " +
                    "(CheeseID, CartID, Quantity) " +
                    "VALUES (" + cheese.getCheeseId() + ", " +
                    cart.getId() + ", " + cheese.getQuantity() + ");");
        } catch (SQLException e) {
            System.out.println("Exception while inserting data...");
        } finally {
            closeStatement(statement);
        }
    }

    private CartEntry buildMultiCheese(ResultSet result) throws SQLException {
        int cheeseID = result.getInt("CheeseID");
        Cheese cheese = cheeseDAO.getCheese(cheeseID);
        return new CartEntry(cheese, result.getInt("Quantity"));
    }
}
