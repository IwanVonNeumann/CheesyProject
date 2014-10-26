package dao.base;

import dao.iface.ConnectionManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by IRuskevich on 20.03.2014
 */
public abstract class DAOTest {

    private static String url;
    private static String name;
    private static String password;

    protected static String entity;

    protected static Connection connection;

    protected static ConnectionManager connectionManager;

    @BeforeClass
    public static void prepareData() {
        url = "jdbc:mysql://localhost/cheese_test";
        name = "user";
        password = "userpwd";

        connect();

//        connectionManager = new JDBCConnectionManager();
    }

    @AfterClass
    public static void discardData() {
        disconnect(connection);
    }

    private static void connect() {
        try {
            System.out.println("Connecting to the Test DB...\n");
            Class.forName("com.mysql.jdbc.Driver"); //Загружаем драйвер
            connection = DriverManager.getConnection(url, name, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void disconnect(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            System.out.println("Exception while closing Connection...");
        }
        System.out.println("\nSuccessfully disconnected from the DB...");
    }

    protected static void executeFile(String entity, String fileName) {
        try {
            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    new File("./src/main/resources/sql/test/" +
                                            entity + "/" + fileName)));

            StringBuilder stringBuilder = new StringBuilder();

            for (String line; (line = br.readLine()) != null; ) {
                stringBuilder.append(line);
            }

            String[] queries = stringBuilder.toString().split(";");

            Statement statement = connection.createStatement();

            for (String s : queries) {
                s += ";";
                System.out.println("[JDBC] " + s);
                statement.executeUpdate(s);
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    protected <T> boolean itemInList(List<T> list, T item) {
        for (T current : list) {
            if (current.equals(item)) return true;
        }
        return false;
    }

    protected <T> boolean listsEqual(List<T> list1, List<T> list2) {
        if (list1.size() != list2.size()) return false;
        for (T item : list1) {
            if (!itemInList(list2, item)) return false;
        }
        return true;
    }
}