package dao.jdbc.dao;

import dao.iface.AddressDAO;
import dao.iface.ProxyFactory;
import dao.jdbc.mappers.AddressRowMapper;
import domain.Address;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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

    @Override
    public Address getAddress(long id) {

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

    @Override
    public void insertAddress(final Address address) {
        if (exists(address)) return; // уже в базе

        System.out.println("[JDBC] INSERT INTO Customers (CustomerName, Street, ZipCode, City)\n" +
                "\t\tVALUES (\"" + address.getName() + "\", \"" +
                address.getStreet() + "\", " + address.getZipCode() + ", " +
                address.getCity() + ", hash);");

        final String query = "INSERT INTO Customers " +
                "(CustomerName, Street, ZipCode, City, PasswordHash, Deleted) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, address.getName());
                statement.setString(2, address.getStreet());
                statement.setInt(3, address.getZipCode());
                statement.setString(4, address.getCity());
                statement.setBytes(5, address.getHash());
                statement.setBoolean(6, false);
                return statement;
            }
        }, holder);

        address.setId(holder.getKey().longValue());
        System.out.println("[JDBC] ID granted: " + address.getId());
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

        System.out.println("[JDBC] SELECT 1 FROM Customers\n\t" +
                "WHERE CustomerName = " + address.getName() + " AND Deleted <> true");

        String query = "SELECT COUNT(CustomerID) FROM Customers WHERE CustomerName = ? AND Deleted <> true";

        int count = jdbcTemplate.queryForObject(query, new Object[]{address.getId()}, Integer.class);

        return count > 0;
    }
}
