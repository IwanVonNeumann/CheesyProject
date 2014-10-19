package dao.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Iwan on 19.10.2014
 */

//TODO refactor to ConnectionPool
public class ConnectionWrapper {

    private DataSource dataSource;

    private Connection connection;

    public ConnectionWrapper() {
    }

    public void init() {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() {
        return connection;
    }
}
