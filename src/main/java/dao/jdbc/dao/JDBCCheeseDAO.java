package dao.jdbc.dao;

import dao.iface.CheeseDAO;
import dao.iface.ProxyFactory;
import dao.jdbc.mappers.CheeseRowMapper;
import domain.Cheese;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JDBCCheeseDAO extends JDBCDAO implements CheeseDAO {

    private ProxyFactory proxyFactory;

    public JDBCCheeseDAO() {
        super();
        System.out.println("[JDBC] Creating Cheese DAO...");
    }

    public void setProxyFactory(ProxyFactory proxyFactory) {
        this.proxyFactory = proxyFactory;
    }

    @Override
    public List<Cheese> getCheesesList() {

        System.out.println("[JDBC] SELECT * FROM Cheeses\n" +
                "\tWHERE Deleted <> true;");

        String query = "SELECT * FROM Cheeses WHERE Deleted <> ?";

        List<Cheese> cheesesList = jdbcTemplate.query(query, new Object[]{true}, new CheeseRowMapper());

        return proxyFactory.getCheeseProxyList(cheesesList);
//      return jdbcTemplate.queryForList(query, Cheese.class); //TODO
    }

    //TODO search for usages
    @Override
    public Cheese getCheese(long id) {

        System.out.println("[JDBC] SELECT * FROM Cheeses " +
                "WHERE CheeseID = " + id + ";");

        String query = "SELECT * FROM Cheeses WHERE CheeseID = ?";

        Cheese cheese = jdbcTemplate.queryForObject(query, new Object[]{id}, new CheeseRowMapper());

        return proxyFactory.getCheeseProxy(cheese);
    }

    @Override
    public void safeDeleteCheese(Cheese cheese) {

        System.out.println("[JDBC] UPDATE Cheeses \n" +
                "\tSET Deleted = true \n" +
                "\tWHERE CheeseID = " + cheese.getId());

        String query = "UPDATE Cheeses SET Deleted = true WHERE CheeseID = ?";

        jdbcTemplate.update(query, cheese.getId());
    }

    @Override
    public void addCheese(final Cheese cheese) {
        if (exists(cheese)) return; // уже в базе

        System.out.println("[JDBC] INSERT INTO Cheeses (CheeseName, Description, Price)\n" +
                    "\t\tVALUES (\"" + cheese.getName() + "\", \"" +
                    cheese.getDescription() + "\", " + cheese.getPrice() + ")");

        final String query = "INSERT INTO Cheeses (CheeseName, Description, Price, Deleted) " +
                "VALUES (?, ?, ?, ?)";

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, cheese.getName());
                statement.setString(2, cheese.getDescription());
                statement.setDouble(3, cheese.getPrice());
                statement.setBoolean(4, false);
                return statement;
            }
        }, holder);

        cheese.setId(holder.getKey().longValue());
        System.out.println("[JDBC] ID granted: " + cheese.getId());
    }

    @Override
    public void updateCheese(Cheese cheese) {

        System.out.println("[JDBC] UPDATE Cheeses\n" +
                "\t\tSET CheeseName = \"" + cheese.getName() + "\", " +
                "Description = \"" + cheese.getDescription() + "\", " +
                "Price = " + cheese.getPrice() + "\n" +
                "\t\tWHERE CheeseID = " + cheese.getId() + ";");

        String query = "UPDATE Cheeses " +
                "SET CheeseName = ?, Description = ?, Price = ? " +
                "WHERE CheeseID = ?";

        jdbcTemplate.update(query,
                cheese.getName(), cheese.getDescription(), cheese.getPrice(), cheese.getId());
    }

    // TODO possible query review
    @Override
    public boolean exists(Cheese cheese) {

        System.out.println("[JDBC] SELECT 1 FROM Cheeses\n" +
                "\tWHERE CheeseName = " + cheese.getName() + " AND Deleted <> true");

        String query = "SELECT 1 FROM Cheeses WHERE CheeseName = ? AND Deleted <> true";

        List<Cheese> cheesesList = jdbcTemplate.query(query,
                new Object[]{cheese.getName()}, new CheeseRowMapper());

        return cheesesList.size() > 0;
    }

    @Override
    public List<Cheese> searchCheeseByName(String key) {

        System.out.println("[JDBC] SELECT * FROM Cheeses\n" +
                "\tWHERE Deleted <> true\n" +
                "\tAND CheeseName LIKE %" + key + "%;");

        String query = "SELECT * FROM Cheeses WHERE Deleted <> true AND CheeseName LIKE ?";

        List<Cheese> cheesesList = jdbcTemplate.query(query,
                new Object[]{"%" + key + "%"}, new CheeseRowMapper());

        return proxyFactory.getCheeseProxyList(cheesesList);
    }

    @Override
    public List<Cheese> searchCheeseByDescription(String key) {

        System.out.println("[JDBC] SELECT * FROM Cheeses\n" +
                "\tWHERE Deleted <> true\n" +
                "\tAND Description LIKE %" + key + "%;");

        String query = "SELECT * FROM Cheeses WHERE Deleted <> true AND Description LIKE ?";

        List<Cheese> cheesesList = jdbcTemplate.query(query,
                new Object[]{"%" + key + "%"}, new CheeseRowMapper());

        return proxyFactory.getCheeseProxyList(cheesesList);
    }
}
