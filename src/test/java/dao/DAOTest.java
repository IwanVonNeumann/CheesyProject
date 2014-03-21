package dao;

import dao.iface.ConnectionManager;
import dao.jdbc.JDBCConnectionManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.*;

/**
 * Created by IRuskevich on 20.03.2014
 */
public class DAOTest {

    @BeforeClass
    public static void prepareData() {

        ConnectionManager cm = new JDBCConnectionManager("cheese_test");

        File file = new File("./src/main/resources/sql/CreateTables.sql");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            for (String line; (line = br.readLine()) != null; ) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void discardData() {

    }
}