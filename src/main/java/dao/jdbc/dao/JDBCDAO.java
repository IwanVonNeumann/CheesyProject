package dao.jdbc.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class JDBCDAO {

    protected Connection connection;

    private DataSource dataSource;

    protected JDBCDAO() {
    }

    public void init() {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("Exceptions while getting connection...");
        }
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    protected void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception e) {
            System.out.println("Exception while closing ResultSet...");
        }
    }

    protected void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (Exception e) {
            System.out.println("Exception while closing Statement...");
        }
    }
}