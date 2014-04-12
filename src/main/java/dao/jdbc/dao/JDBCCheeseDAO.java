package dao.jdbc.dao;

import dao.iface.CheeseDAO;
import domain.Cheese;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCCheeseDAO extends JDBCDAO implements CheeseDAO {

    public JDBCCheeseDAO(Connection connection) {
        super(connection);
        System.out.println("[JDBC] Creating Cheese DAO...");
    }

    @Override
    public List<Cheese> getCheesesList() {
        List<Cheese> list;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = connection.prepareStatement(
                    "SELECT * FROM Cheeses WHERE Deleted <> ?;");
            statement.setBoolean(1, true);
            result = statement.executeQuery();

            System.out.println("[JDBC] SELECT * FROM Cheeses\n" +
                    "\tWHERE Deleted <> true;");
            list = new ArrayList<>();
            while (result.next()) {
                Cheese cheese = buildCheese(result);
                list.add(cheese);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Exception while accessing data...");
            System.out.println(e);
            return null;
        } finally {
            closeResultSet(result);
            closeStatement(statement);
        }
    }

    @Override
    public Cheese getCheese(int id) {
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            //System.out.println("Searching for Cheese by ID...");
            statement = connection.prepareStatement(
                    "SELECT * FROM Cheeses " +
                            "WHERE CheeseID = ?;");
            statement.setInt(1, id);
            result = statement.executeQuery();
            System.out.println("[JDBC] SELECT * FROM Cheeses " +
                    "WHERE CheeseID = " + id + ";");
            result.next();
            return buildCheese(result);
        } catch (SQLException e) {
            System.out.println("Exception while accessing data...");
            return null;
        } finally {
            closeResultSet(result);
            closeStatement(statement);
        }
    }

    @Override
    public void safeDeleteCheese(Cheese cheese) {
        PreparedStatement statement = null;

        try {
            //System.out.println("Safely Deleting Cheese with ID " + cheese.getId() + "...");
            statement = connection.prepareStatement(
                    "UPDATE Cheeses " +
                            "SET Deleted = ? " +
                            "WHERE CheeseID = ?;");
            statement.setBoolean(1, true);
            statement.setInt(2, cheese.getId());
            statement.executeUpdate();
            System.out.println("[JDBC] UPDATE Cheeses \n" +
                    "\tSET Deleted = true \n" +
                    "\tWHERE CheeseID = " + cheese.getId());
        } catch (SQLException e) {
            System.out.println("Exception while updating data...");
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void addCheese(Cheese cheese) {
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
        PreparedStatement statement = null;

        try {
            //System.out.println("Updating cheese with ID " + cheese.getId() + "...");
            statement = connection.prepareStatement(
                    "UPDATE Cheeses " +
                            "SET CheeseName = ?, " +
                            "Description = ?, " +
                            "Price = ? " +
                            "WHERE CheeseID = ?;");
            statement.setString(1, cheese.getName());
            statement.setString(2, cheese.getDescription());
            statement.setDouble(3, cheese.getPrice());
            statement.setInt(4, cheese.getId());
            statement.executeUpdate();
            System.out.println("[JDBC] UPDATE Cheeses\n" +
                    "\t\tSET CheeseName = \"" + cheese.getName() + "\", " +
                    "Description = \"" + cheese.getDescription() + "\", " +
                    "Price = " + cheese.getPrice() + "\n" +
                    "\t\tWHERE CheeseID = " + cheese.getId() + ";");
        } catch (SQLException e) {
            System.out.println("Exception while updating data...");
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public boolean exists(Cheese cheese) {
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = connection.prepareStatement(
                    "SELECT 1 FROM Cheeses WHERE CheeseName = ? AND Deleted <> true;");
            statement.setString(1, cheese.getName());
            System.out.println("[JDBC] SELECT 1 FROM Cheeses\n" +
                    "\tWHERE CheeseName = " + cheese.getName() +
                    " AND Deleted <> true");
            result =  statement.executeQuery();
            return result.next();
        } catch (SQLException e) {
            System.out.println("Exception while searching for data...");
            return false;
        } finally {
            closeResultSet(result);
            closeStatement(statement);
        }
    }

    private Cheese buildCheese(ResultSet result) throws SQLException {
        return new Cheese(result.getInt("CheeseID"),
                result.getString("CheeseName"),
                result.getString("Description"),
                result.getDouble("Price"),
                result.getBoolean("Deleted"));
    }

    /*
    // включая безопасно удаленные
    @Override
    public List<Cheese> getFullCheesesList() {
        List<Cheese> list;
        Statement statement = null;
        ResultSet result = null;

        try {
            //System.out.println("Accessing data...");
            statement = connection.createStatement();
            result = statement.executeQuery( //Выполним запрос
                    "SELECT * FROM Cheeses");
            System.out.println("[JDBC] SELECT * FROM Cheeses;");
            //result это указатель на первую строку с выборки
            //чтобы вывести данные мы будем использовать
            //метод next() , с помощью которого переходим к следующему элементу
            list = new ArrayList<Cheese>();
            while (result.next()) {
                Cheese cheese = buildCheese(result);
                list.add(cheese);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Exception while accessing data...");
            return null;
        } finally {
            closeResultSet(result);
            closeStatement(statement);
        }
    }*/

    /*public void deleteCheese(Cheese cheese) {

        PreparedStatement statement = null;

        try {
            //System.out.println("Deleting cheese " + cheese.getName() + "...");
            statement = connection.prepareStatement(
                    "DELETE FROM Cheeses WHERE CheeseName = ?;");
            statement.setString(1, cheese.getName());
            statement.executeUpdate();
            System.out.println("[JDBC] DELETE FROM Cheeses WHERE CheeseName = \"" +
                    cheese.getName() + "\";");

        } catch (SQLException e) {
            System.out.println("Exception while deleting data...");
        } finally {
            closeStatement(statement);
        }
    }*/


}
