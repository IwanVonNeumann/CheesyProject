package dao.jdbc.dao;

import dao.iface.CheeseDAO;
import dao.iface.ProxyFactory;
import dao.jdbc.mappers.CheeseRowMapper;
import domain.Cheese;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public Cheese getCheese(int id) {

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

    //TODO -> jdbcTemplate
    @Override
    public void addCheese(final Cheese cheese) {
        if (exists(cheese)) return; // уже в базе

        PreparedStatement statement = null;
        ResultSet generatedKeys;

        try {
            //System.out.println("Adding cheese " + cheese.getName() + "...");
            statement = connection.prepareStatement(
                    "INSERT INTO Cheeses (CheeseName, Description, Price, Deleted) " +
                            "VALUES (?, ?, ?, ?);");
            statement.setString(1, cheese.getName());
            statement.setString(2, cheese.getDescription());
            statement.setDouble(3, cheese.getPrice());
            statement.setBoolean(4, false);
            statement.executeUpdate();
            System.out.println("[JDBC] INSERT INTO Cheeses (CheeseName, Description, Price)\n" +
                    "\t\tVALUES (\"" + cheese.getName() + "\", \"" +
                    cheese.getDescription() + "\", " + cheese.getPrice() + ");");

            generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            cheese.setId(generatedKeys.getInt(1));

            System.out.println("ID granted: " + cheese.getId());
        } catch (SQLException e) {
            System.out.println("Exception while inserting data...");
        } finally {
            closeStatement(statement);
        }
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
