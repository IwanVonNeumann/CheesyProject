package dao.jdbc.dao;

import dao.iface.AddressDAO;
import dao.iface.ProxyFactory;
import dao.jdbc.mappers.AddressRowMapper;
import domain.Address;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JDBCAddressDAO extends JDBCDAO implements AddressDAO {

    private ProxyFactory proxyFactory;

    public JDBCAddressDAO() {
        super();
        System.out.println("[JDBC] Creating Address DAO...");
    }

    public void setProxyFactory(ProxyFactory proxyFactory) {
        this.proxyFactory = proxyFactory;
    }

    @Override
    public List<Address> getAddressesList() {

        System.out.println("[JDBC] SELECT * FROM Customers\n" +
                "\tWHERE Deleted <> true;");

        String query = "SELECT * FROM Customers WHERE Deleted <> ?";

        List<Address> addressList = jdbcTemplate.query(query, new Object[]{true}, new AddressRowMapper());

        return proxyFactory.getAddressProxyList(addressList);
    }

    //TODO search for usages
    @Override
    public Address getAddress(int id) {

        System.out.println("[JDBC] SELECT * FROM Customers " +
                "WHERE CustomerID = " + id + ";");

        String query = "SELECT * FROM Customers WHERE CustomerID = ?";

        Address address = jdbcTemplate.queryForObject(query, new Object[]{id}, new AddressRowMapper());

        return proxyFactory.getAddressProxy(address);
    }

    @Override
    public Address getAddress(String name) {

        System.out.println("[JDBC] SELECT * FROM Customers " +
                "WHERE CustomerName = " + name + ";");

        String query = "SELECT * FROM Customers WHERE CustomerName = ?";

        Address address = jdbcTemplate.queryForObject(query, new Object[]{name}, new AddressRowMapper());

        return proxyFactory.getAddressProxy(address);
    }

    //TODO -> jdbcTemplate
    @Override
    public void insertAddress(Address address) {
        if (exists(address)) return; // уже в базе

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

    @Override
    public void updateAddress(Address address) {

        System.out.println("[JDBC] UPDATE Customers \n" +
                "\tSET CustomerName = " + address.getName() + ", " +
                "Street = " + address.getStreet() + ", " +
                "ZipCode = " + address.getZipCode() + ", " +
                "City = " + address.getCity() + "\n" +
                "PasswordHash = hash\n" +
                "\tWHERE CustomerID = " + address.getId());

        String query = "UPDATE Customers " +
                "SET CustomerName = ?, Street = ?, ZipCode = ?, City = ?, PasswordHash = ? " +
                "WHERE CustomerID = ?";

        jdbcTemplate.update(query,
                address.getName(), address.getStreet(), address.getZipCode(),
                address.getCity(), address.getHash(), address.getId());
    }

    @Override
    public void safeDeleteAddress(Address address) {

        System.out.println("[JDBC] UPDATE Customers \n" +
                "\tSET Deleted = true \n" +
                "\tWHERE CustomerID = " + address.getId() + ";");

        String query = "UPDATE Customers SET Deleted = true WHERE CustomerID = ?";

        jdbcTemplate.update(query, address.getId());
    }

    @Override
    public boolean exists(Address address) {

        System.out.println("[JDBC] SELECT 1 FROM Customers\n" +
                "\tWHERE CustomerName = " + address.getName() +
                " AND Deleted <> true");

        String query = "SELECT 1 FROM Customers WHERE CustomerName = ? AND Deleted <> true";

        List<Address> addressList = jdbcTemplate.query(query,
                new Object[]{address.getName()}, new AddressRowMapper());

        return addressList.size() > 0;
    }
}
