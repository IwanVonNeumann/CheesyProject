package dao;

import domain.MultiCheese;
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

    @BeforeClass
    public static void prepareData() {
        url = "jdbc:mysql://localhost/cheese_test";
        name = "user";
        password = "userpwd";

        connect();
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected boolean listsEqual(List<MultiCheese> list1, List<MultiCheese> list2) {
        if (list1.size() != list2.size()) return false;
        for (MultiCheese cheese : list1) {

        }
        return false;
    }
}