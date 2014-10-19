package dao.jdbc.dao;

import dao.jdbc.ConnectionWrapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class JDBCDAO {

    private ConnectionWrapper connectionWrapper;

    protected Connection connection;

    protected JdbcTemplate jdbcTemplate;

    protected JDBCDAO() {
    }

    public void init() {
        connection = connectionWrapper.getConnection();
    }

    public void setConnectionWrapper(ConnectionWrapper connectionWrapper) {
        this.connectionWrapper = connectionWrapper;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // TODO прочитать о необходимости в JDBCTemplate
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
