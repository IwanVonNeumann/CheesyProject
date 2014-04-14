package dao.hiber;

import dao.base.AddressDAOTest;
import dao.iface.ConnectionManager;

/**
 * Created by Iwan on 13.04.2014
 */
public class HiberAddressDAOTest extends AddressDAOTest {

    @Override
    public void initDAO() {
        executeFile(entity, "createTables.sql");
        executeFile(entity, "insertData.sql");

        ConnectionManager cm = new HiberConnectionManager();
        dao = cm.getTestConnection().getAddressDAO();
    }
}
