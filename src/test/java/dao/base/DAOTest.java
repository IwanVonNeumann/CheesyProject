package dao.base;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by IRuskevich on 20.03.2014
 */

public abstract class DAOTest {

    protected static String entity;

    protected static Connection connection;

    @BeforeClass
    public static void prepareData() {

        System.out.println("DAOTest.prepareData called");

        ApplicationContext context = new ClassPathXmlApplicationContext("test-context.xml");

        DataSource dataSource = (DataSource) context.getBean("dataSource");

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void discardData() {
        // TODO drop
    }

    protected static void executeFile(String entity, String fileName) {
        try {
            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    new File("classpath:sql/" +
                                            entity + "/" + fileName)));

            StringBuilder stringBuilder = new StringBuilder();

            for (String line; (line = br.readLine()) != null; ) {
                stringBuilder.append(line);
            }

            String[] queries = stringBuilder.toString().split(";");

            Statement statement = connection.createStatement();

            for (String s : queries) {
                s += ";";
                System.out.println("[JDBC] " + s);
                statement.executeUpdate(s);
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    protected <T> boolean itemInList(List<T> list, T item) {
        for (T current : list) {
            if (current.equals(item)) return true;
        }
        return false;
    }

    protected <T> boolean listsEqual(List<T> list1, List<T> list2) {
        if (list1.size() != list2.size()) return false;
        for (T item : list1) {
            if (!itemInList(list2, item)) return false;
        }
        return true;
    }
}