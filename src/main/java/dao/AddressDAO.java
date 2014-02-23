package dao;

import domain.Address;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO extends DAO {

    public AddressDAO(Connection connection) {
        super(connection);
        System.out.println("Creating Address DAO...");
    }

    public List<Address> getAddressesList() {
        List<Address> list = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            //System.out.println("Accessing data...");
            statement = connection.createStatement();
            result = statement.executeQuery(
                    "SELECT * FROM Customers;");
            System.out.println("[JDBC] SELECT * FROM Customers");
            list = new ArrayList<Address>();
            while (result.next()) {
                Address address = new Address(result.getString("CustomerName"),
                        result.getString("Street"),
                        result.getString("City"),
                        result.getInt("ZipCode"),
                        result.getInt("CustomerID")
                );
                list.add(address);
            }
        } catch (SQLException e) {
            System.out.println("Exception while accessing data...");
        } finally {
            closeResultSet(result);
            closeStatement(statement);
            return list;
        }
    }

    public void insertAddress(Address address) {
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;

        try {
            //System.out.println("Adding cheese " + cheese.getName() + "...");
            statement = connection.prepareStatement(
                    "INSERT INTO Customers (CustomerName, Street, ZipCode, City) " +
                            "VALUES (?, ?, ?, ?);");

            statement.setString(1, address.getName());
            statement.setString(2, address.getStreet());
            statement.setInt(3, address.getZipCode());
            statement.setString(4, address.getCity());
            statement.executeUpdate();
            System.out.println("[JDBC] INSERT INTO Customers (CustomerName, Street, ZipCode, City)\n" +
                    "\t\tVALUES (\"" + address.getName() + "\", \"" +
                    address.getStreet() + "\", " + address.getZipCode() + ", " +
                    address.getCity() + ");");
            // извлечение ID из базы
            //address.setId(getAddressId(address));
            generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            address.setId(generatedKeys.getInt(1));

            System.out.println("ID granted: " + address.getId());
        } catch (SQLException e) {
            System.out.println("Exception while inserting data...");
        } finally {
            closeStatement(statement);
            closeResultSet(generatedKeys);
        }
    }

    public Address getAddress(int id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        Address address = null;

        try {
            //System.out.println("Accessing data...");
            statement = connection.prepareStatement(
                    "SELECT * FROM Customers " +
                            "WHERE CustomerID = ?;");
            statement.setInt(1, id);
            result = statement.executeQuery();
            result.next();
            address = new Address(result.getString("CustomerName"),
                    result.getString("Street"),
                    result.getString("City"),
                    result.getInt("ZipCode"),
                    result.getInt("CustomerID")
            );

        } catch (SQLException e) {
            System.out.println("Exception while accessing data...");
        } finally {
            closeResultSet(result);
            closeStatement(statement);
            return address;
        }
    }


    // заменено
    /*
    private int getAddressId(Address address) {
        PreparedStatement statement = null;
        ResultSet result = null;

        int id = 0;

        try {
            //System.out.println("Accessing data...");
            statement = connection.prepareStatement(
                    "SELECT CustomerID FROM Customers " +
                            "WHERE CustomerName = ? AND Street = ? AND " +
                            "ZipCode = ? AND City = ?;");
            statement.setString(1, address.getName());
            statement.setString(2, address.getStreet());
            statement.setInt(3, address.getZipCode());
            statement.setString(4, address.getCity());
            result = statement.executeQuery();
            result.next();
            id = result.getInt("CustomerID");
            //return id;

        } catch (SQLException e) {
            System.out.println("Exception while accessing data...");
        } finally {
            closeResultSet(result);
            closeStatement(statement);
            return id;
        }
    }
    */

}
