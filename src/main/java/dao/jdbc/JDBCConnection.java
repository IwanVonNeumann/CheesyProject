package dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnection {

    private Connection connection;

    public JDBCConnection() {
        System.out.println("[JDBC] Connecting to DB...");
        String url = "jdbc:mysql://localhost/cheesydb"; //URL к базе состоит из протокола:подпротокола://[хоста]:[порта_СУБД]/[БД] и других_сведений
        String name = "user"; //Имя пользователя БД
        String password = "userpwd"; //Пароль
        try {
            Class.forName("com.mysql.jdbc.Driver"); //Загружаем драйвер
            connection = DriverManager.getConnection(url, name, password); //Создаём соединение
        } catch (Exception e) {
            System.out.println("DB connection failed...");
            disconnect();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void disconnect() {
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
