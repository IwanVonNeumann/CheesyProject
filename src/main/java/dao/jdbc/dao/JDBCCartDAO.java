package dao.jdbc.dao;

import dao.iface.AddressDAO;
import dao.iface.CartDAO;
import dao.iface.CartEntryDAO;

import domain.Address;
import domain.Cart;
import domain.MultiCheese;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCCartDAO extends JDBCDAO implements CartDAO {

    private AddressDAO addressDAO;
    private CartEntryDAO cartEntryDAO;

    public JDBCCartDAO(Connection connection) {
        super(connection);
        addressDAO = new JDBCAddressDAO(connection);
        cartEntryDAO = new JDBCCartEntryDAO(connection);
        System.out.println("[JDBC] Creating Cart DAO...");
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
            System.out.println("[JDBC] SELECT * FROM Carts;");
            list = new ArrayList<Cart>();
            while (result.next()) {
                /*Cart cart = new Cart(
                        result.getTimestamp("Clock"),
                        result.getInt("CartID"),
                        result.getInt("CustomerID")
                );*/
                int cartId = result.getInt("CartID");
                List<MultiCheese> cheeses = cartEntryDAO.getCartEntries(cartId);

                int customerId = result.getInt("CustomerID");
                Address address = addressDAO.getAddress(customerId);

                Cart cart = new Cart(cartId, cheeses, address,
                        result.getTimestamp("Clock"));
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

    public List<Cart> getCartsList(Address address) {
        List<Cart> list = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            //System.out.println("Accessing data...");
            statement = connection.prepareStatement(
                    "SELECT * FROM Carts WHERE CustomerID = ?;");
            statement.setInt(1, address.getId());
            result = statement.executeQuery();
            System.out.println("[JDBC] SELECT * FROM Carts\n\t" +
                    "WHERE CustomerID = " + address.getId() + ";");
            list = new ArrayList<Cart>();
            while (result.next()) {
                /*Cart cart = new Cart(result.getTimestamp("Clock"),
                        result.getInt("CartID"),
                        result.getInt("CustomerID")
                );*/
                int cartId = result.getInt("CartID");
                List<MultiCheese> cheeses = cartEntryDAO.getCartEntries(cartId);

                Cart cart = new Cart(cartId, cheeses, address,
                        result.getTimestamp("Clock"));

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

    //public void insertCart(Address address, Cart cart) {
    // TODO организовать каскадную вставку элементов
    public void insertCart(Cart cart) {
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;

        try {
            statement = connection.prepareStatement(
                    "INSERT INTO Carts (Clock, CustomerID) " +
                            "VALUES (?, ?);");
            statement.setTimestamp(1, cart.getTime());
            statement.setInt(2, cart.getAddress().getId());
            statement.executeUpdate();

            generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            cart.setId(generatedKeys.getInt(1));

            System.out.println("[JDBC] INSERT INTO Carts " +
                    "(Clock, CustomerID)\n" +
                    "\t\tVALUES (date, \"" + cart.getAddress().getId() + "\");");
        } catch (SQLException e) {
            System.out.println("Exception while inserting data...");
        } finally {
            closeStatement(statement);
            closeResultSet(generatedKeys);
        }
    }
}
