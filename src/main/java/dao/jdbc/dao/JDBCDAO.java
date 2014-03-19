package dao.jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class JDBCDAO {

    protected Connection connection;

    public JDBCDAO(Connection connection) {
        this.connection = connection;
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
