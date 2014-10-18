import cache.iface.IDataCache;
import cache.mock.DataCacheMock;
import dao.iface.ConnectionManager;
import domain.Cheese;
import domain.Comment;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by IRuskevich on 16.04.2014
 */
public class TestTest {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/applicationContext.xml");

        ConnectionManager connectionManager = (ConnectionManager)context.getBean("connectionManager");

        IDataCache dataCache = new DataCacheMock();

        List<Cheese> cheeses = dataCache.getCheesesList();

        Cheese cheese = cheeses.get(0);

        System.out.println(cheese);

        System.out.println("Comments: " + cheese.getComments().size());

        for (Comment comment : cheese.getComments()) {
            System.out.println(comment);
        }
    }
}
