package dao.jdbc;

import dao.base.AddressDAOTest;
import dao.jdbc.dao.JDBCAddressDAO;

/**
 * Created by Iwan on 13.04.2014
 */

public class JDBCAddressDAOTest extends AddressDAOTest {

    @Override
    public void initDAO() {
        executeFile(entity, "createTables.sql");
        executeFile(entity, "insertData.sql");
        dao = new JDBCAddressDAO(connection);
    }
}