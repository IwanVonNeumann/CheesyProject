package dao.jdbc.dao;

import dao.iface.AddressDAO;
import dao.jdbc.proxy.JDBCAddressProxy;
import domain.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCAddressDAO extends JDBCDAO implements AddressDAO {

    Connection connection;

    public JDBCAddressDAO(Connection connection) {
        super(connection);
        this.connection = connection;
        System.out.println("[JDBC] Creating Address DAO...");
    }

    public List<Address> getAddressesList() {
        List<Address> list = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = connection.prepareStatement(
                    "SELECT * FROM Customers WHERE Deleted <> ?;");
            statement.setBoolean(1, true);
            result = statement.executeQuery();

            System.out.println("[JDBC] SELECT * FROM Customers\n" +
                    "\tWHERE Deleted <> true;");
            list = new ArrayList<Address>();
            while (result.next()) {
                //Address address = new Address(result.getString("CustomerName"),
                Address address = new JDBCAddressProxy(result.getString("CustomerName"),
                        result.getString("Street"),
                        result.getString("City"),
                        result.getInt("ZipCode"),
                        result.getInt("CustomerID"),
                        result.getBytes("PasswordHash"),
                        result.getBoolean("deleted"),
                        connection
                );
                list.add(address);
            }
        } catch (SQLException e) {
            System.out.println("Exception while accessing data...");
            System.out.println(e);
        } finally {
            closeResultSet(result);
            closeStatement(statement);
            return list;
        }
    }

    // включая безопасно удаленные
    public List<Address> getFullAddressList() {
        //System.out.println("Accessing data...");
            /*statement = connection.createStatement();
            result = statement.executeQuery(
                    "SELECT * FROM Customers;");*/
        return null; //все адреса, включая удаленные
    }

    public void insertAddress(Address address) {
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;

        try {
            //System.out.println("Adding cheese " + cheese.getName() + "...");
            statement = connection.prepareStatement(
                    "INSERT INTO Customers (CustomerName, Street, ZipCode, City, PasswordHash, Deleted) " +
                            "VALUES (?, ?, ?, ?, ?, ?);");

            statement.setString(1, address.getName());
            statement.setString(2, address.getStreet());
            statement.setInt(3, address.getZipCode());
            statement.setString(4, address.getCity());
            statement.setBytes(5, address.getHash());
            statement.setBoolean(6, false);
            statement.executeUpdate();
            System.out.println("[JDBC] INSERT INTO Customers (CustomerName, Street, ZipCode, City)\n" +
                    "\t\tVALUES (\"" + address.getName() + "\", \"" +
                    address.getStreet() + "\", " + address.getZipCode() + ", " +
                    address.getCity() + ", hash);");
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
            //address = new Address(result.getString("CustomerName"),
            address = new JDBCAddressProxy(result.getString("CustomerName"),
                    result.getString("Street"),
                    result.getString("City"),
                    result.getInt("ZipCode"),
                    result.getInt("CustomerID"),
                    result.getBytes("PasswordHash"),
                    result.getBoolean("Deleted"),
                    connection
            );

        } catch (SQLException e) {
            System.out.println("Exception while accessing data...");
        } finally {
            closeResultSet(result);
            closeStatement(statement);
            return address;
        }
    }

    public Address getAddress(String name) {
        PreparedStatement statement = null;
        ResultSet result = null;
        Address address = null;

        try {
            //System.out.println("Accessing data...");
            statement = connection.prepareStatement(
                    "SELECT * FROM Customers " +
                            "WHERE CustomerName = ?;");
            statement.setString(1, name);
            result = statement.executeQuery();
            result.next();
            //address = new Address(result.getString("CustomerName"),
            address = new JDBCAddressProxy(result.getString("CustomerName"),
                    result.getString("Street"),
                    result.getString("City"),
                    result.getInt("ZipCode"),
                    result.getInt("CustomerID"),
                    result.getBytes("PasswordHash"),
                    result.getBoolean("deleted"),
                    connection
            );

        } catch (SQLException e) {
            System.out.println("Exception while accessing data...");
        } finally {
            closeResultSet(result);
            closeStatement(statement);
            return address;
        }
    }

    public void updateAddress(Address address) {
        PreparedStatement statement = null;

        try {
            //System.out.println("Updating Address with ID " + address.getId() + "...");
            statement = connection.prepareStatement(
                    "UPDATE Customers " +
                            "SET CustomerName = ?, " +
                            "Street = ?, " +
                            "ZipCode = ?, " +
                            "City = ?, " +
                            "PasswordHash = ? " +
                            "WHERE CustomerID = ?;");
            statement.setString(1, address.getName());
            statement.setString(2, address.getStreet());
            statement.setInt(3, address.getZipCode());
            statement.setString(4, address.getCity());
            statement.setBytes(5, address.getHash());
            statement.setInt(6, address.getId());
            statement.executeUpdate();
            System.out.println("[JDBC] UPDATE Customers \n" +
                    "\tSET CustomerName = " + address.getName() + ", " +
                    "Street = " + address.getStreet() + ", " +
                    "ZipCode = " + address.getZipCode() + ", " +
                    "City = " + address.getCity() + "\n" +
                    "PasswordHash = hash\n" +
                    "\tWHERE CustomerID = " + address.getId());
        } catch (SQLException e) {
            System.out.println("Exception while updating data...");
        } finally {
            closeStatement(statement);
        }
    }

    public void safeDeleteAddress(Address address) {
        PreparedStatement statement = null;

        try {
            //System.out.println("Safely Deleting Address with ID " + address.getId() + "...");
            statement = connection.prepareStatement(
                    "UPDATE Customers " +
                            "SET Deleted = ? " +
                            "WHERE CustomerID = ?;");
            statement.setBoolean(1, true);
            statement.setInt(2, address.getId());
            statement.executeUpdate();
            System.out.println("[JDBC] UPDATE Customers \n" +
                    "\tSET Deleted = true \n" +
                    "\tWHERE CustomerID = " + address.getId());
        } catch (SQLException e) {
            System.out.println("Exception while updating data...");
        } finally {
            closeStatement(statement);
        }
    }

    /*
    // работает только если нет покупок
    public void deleteAddress(Address address) {
        PreparedStatement statement = null;

        try {
            //System.out.println("Deleting address " + address.getName() + "...");
            statement = connection.prepareStatement(
                    "DELETE FROM Customers WHERE CustomerID = ?;");
            statement.setInt(1, address.getId());
            statement.executeUpdate();
            System.out.println("[JDBC] DELETE FROM Customers WHERE CustomerID = \"" +
                    address.getId() + "\";");
        } catch (SQLException e) {
            System.out.println("Exception while deleting data...");
        } finally {
            closeStatement(statement);
        }
    }
    */


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
