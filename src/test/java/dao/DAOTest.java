package dao;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by IRuskevich on 20.03.2014
 */
public class DAOTest {

    private static String url;
    private static String name;
    private static String password;

    protected static Connection connection;

    @BeforeClass
    public static void prepareData() {

        url = "jdbc:mysql://localhost/cheese_test";
        name = "user";
        password = "userpwd";

        connect();
        executeFile("CreateTables.sql");
        executeFile("InsertData.sql");
    }


    @AfterClass
    public static void discardData() {
        executeFile("dropTables.sql");
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

    private static void disconnect(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            System.out.println("Exception while closing Connection...");
        }
        System.out.println("\nSuccessfully disconnected from the DB...");
    }

    private static void executeFile(String fileName) {
        try {
            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    new File("./src/main/resources/sql/" + fileName)));

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
}