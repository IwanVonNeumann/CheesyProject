package dao.jdbc;

import dao.iface.CartEntryDAO;
import domain.Cart;
import domain.Cheese;
import domain.MultiCheese;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCCartEntryDAO extends JDBCDAO implements CartEntryDAO {

    private JDBCCheeseDAO cheeseDAO;

    public JDBCCartEntryDAO(Connection connection) {
        super(connection);
        System.out.println("[JDBC] Creating Purchase DAO...");
        cheeseDAO = new JDBCCheeseDAO(connection);
    }

    public void insertCartEntry(Cart cart, MultiCheese cheese) {
        PreparedStatement statement = null;

        try {

            System.out.println("Adding CartEntry for " +
                    cheese.getName() + "...");
            System.out.println("Cart ID: " + cart.getId());
            System.out.println("Cheese ID: " + cheese.getId());
            System.out.println("Quantity: " + cheese.getQuantity());

            statement = connection.prepareStatement(
                    "INSERT INTO CartEntries " +
                            "(CheeseID, CartID, Quantity) " +
                            "VALUES (?, ?, ?);");
            statement.setInt(1, cheese.getId());
            statement.setInt(2, cart.getId());
            statement.setInt(3, cheese.getQuantity());
            statement.executeUpdate();
            System.out.println("[JDBC] INSERT INTO CartEntries " +
                    "(CheeseID, CartID, Quantity) " +
                    "VALUES (" + cheese.getId() + ", " +
                    cart.getId() + ", " + cheese.getQuantity() + ");");
        } catch (SQLException e) {
            System.out.println("Exception while inserting data...");
        } finally {
            closeStatement(statement);
        }
    }

    public List<MultiCheese> getCartEntries(Cart cart) {
        List<MultiCheese> list = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            //System.out.println("Searching for CartEntries by Cart ID...");
            statement = connection.prepareStatement(
                    "SELECT * FROM CartEntries " +
                            "WHERE CartID = ?;");
            statement.setInt(1, cart.getId());
            result = statement.executeQuery();

            System.out.println("[JDBC] SELECT * FROM CartEntries " +
                    "WHERE CartID = " + cart.getId() + ";");

            list = new ArrayList<MultiCheese>();
            while (result.next()) {
                int cheeseID = result.getInt("CheeseID");
                Cheese cheese = cheeseDAO.getCheese(cheeseID);
                MultiCheese multiCheese = new MultiCheese(
                        cheese,
                        result.getInt("Quantity"),
                        result.getInt("CartID")
                );
                list.add(multiCheese);
            }
        } catch (SQLException e) {
            System.out.println("Exception while accessing data...");
        } finally {
            closeResultSet(result);
            closeStatement(statement);
            return list;
        }
    }
}
