package dao.jdbc.dao;

import dao.iface.AddressDAO;
import dao.iface.LikeDAO;
import domain.Address;
import domain.Cheese;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by IRuskevich on 10.05.2014
 */
public class JDBCLikeDAO extends JDBCDAO implements LikeDAO {

    private AddressDAO addressDAO;

    public JDBCLikeDAO(Connection connection) {
        super(connection);
        addressDAO = new JDBCAddressDAO(connection);
        System.out.println("[JDBC] Creating Like DAO...");
    }

    @Override
    public Set<Address> getLikesList(Cheese cheese) {
        List<Integer> addresses;
        Set<Address> list;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            //System.out.println("Accessing data...");

            // Consider
            // SELECT LikeID, CheeseName, CustomerName
            // FROM Cheeses INNER JOIN Likes
            // ON Cheeses.CheeseID = Likes.CheeseID
            // INNER JOIN Customers
            // ON Likes.CustomerID = Customers.CustomerID;

            statement = connection.prepareStatement(
                    "SELECT * FROM Likes WHERE CheeseID = ?;");
            statement.setInt(1, cheese.getId());
            result = statement.executeQuery();
            System.out.println("[JDBC] SELECT * FROM Likes\n\t" +
                    "WHERE CheeseID = " + cheese.getId() + ";");
            addresses = new LinkedList<>();
            while (result.next()) {
                addresses.add(result.getInt("CustomerID"));
            }
            list = new HashSet<>(addresses.size());
            for (Integer i : addresses) {
                list.add(addressDAO.getAddress(i));
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
    public void insertLike(Cheese cheese, Address address) {
        PreparedStatement statement = null;

        try {
            System.out.println("Adding Like for " +
                    cheese.getName() + "...");
            System.out.println("Cheese ID: " + cheese.getId());
            System.out.println("Cheese name: " + cheese.getName());
            System.out.println("Customer: " + address.getName());

            statement = connection.prepareStatement(
                    "INSERT INTO Likes " +
                            "(CheeseID, CustomerID) " +
                            "VALUES (?, ?);");
            statement.setInt(1, cheese.getId());
            statement.setInt(2, address.getId());

            statement.executeUpdate();
            System.out.println("[JDBC] INSERT INTO Likes\n" +
                    "\t(CheeseID, CustomerID)\n" +
                    "\tVALUES (" + cheese.getId() + ", " + address.getId() + ");");
        } catch (SQLException e) {
            System.out.println("Exception while inserting data...");
        } finally {
            closeStatement(statement);
        }
    }
}