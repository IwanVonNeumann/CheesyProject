package dao.jdbc;

import dao.base.CheeseDAOTest;
import dao.jdbc.dao.JDBCCheeseDAO;

/**
 * Created by Iwan on 13.04.2014
 */

public class JDBCCheeseDAOTest extends CheeseDAOTest {

    @Override
    public void initDAO() {
        executeFile(entity, "createTables.sql");
        executeFile(entity, "insertData.sql");
        dao = new JDBCCheeseDAO(connection);
    }
}
