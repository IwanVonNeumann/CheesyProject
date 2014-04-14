package dao.jdbc;

import dao.iface.ConnectionManager;
import dao.iface.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnectionManager implements ConnectionManager {

    private String url; //URL к базе состоит из протокола:подпротокола://[хоста]:[порта_СУБД]/[БД] и других_сведений
    private String testUrl;
    private String name; //Имя пользователя БД
    private String password; //Пароль

    public JDBCConnectionManager() {
        System.out.println("[JDBC] Connecting to DB...");
        url = "jdbc:mysql://localhost/cheesydb";
        testUrl = "jdbc:mysql://localhost/cheese_test";
        name = "user";
        password = "userpwd";
    }

    @Override
    public DBConnection getConnection() {
        return prepareConnection(url);
    }

    @Override
    public DBConnection getTestConnection() {
        return prepareConnection(testUrl);
    }

    private DBConnection prepareConnection(String DBurl) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver"); //Загружаем драйвер
            connection = DriverManager.getConnection(DBurl, name, password);
            return new JDBCConnection(connection);
        } catch (Exception e) {
            System.out.println("DB connection failed...");
            disconnect(connection); // always null?
            return null;
        }
    }

    public void disconnect(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            System.out.println("Exception while closing Connection...");
        }
        System.out.println("\nSuccessfully disconnected from the DB...");
    }
}