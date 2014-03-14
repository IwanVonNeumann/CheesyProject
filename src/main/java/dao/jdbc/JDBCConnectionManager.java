package dao.jdbc;

import dao.iface.ConnectionManager;
import dao.iface.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnectionManager implements ConnectionManager {

    String url; //URL к базе состоит из протокола:подпротокола://[хоста]:[порта_СУБД]/[БД] и других_сведений
    String name; //Имя пользователя БД
    String password; //Пароль

    public JDBCConnectionManager() {
        System.out.println("[JDBC] Connecting to DB...");
        url = "jdbc:mysql://localhost/cheesydb";
        name = "user";
        password = "userpwd";
    }

    public DBConnection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver"); //Загружаем драйвер
            connection = DriverManager.getConnection(url, name, password);
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
