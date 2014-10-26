package dao.jdbc.dao;

import dao.jdbc.ConnectionWrapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;

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
}
