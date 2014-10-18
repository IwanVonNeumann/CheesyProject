package war;

import models.loaders.ModelLoader;
import org.apache.wicket.*;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import search.SearchEngine;

public class CheeseApplication extends WebApplication {

    private ModelLoader modelLoader;

    private SearchEngine searchEngine;

    public CheeseApplication() {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        modelLoader = (ModelLoader)context.getBean("modelLoader");

        searchEngine = (SearchEngine)context.getBean("searchEngine");
    }

    @Override
    protected void init() {
    }

    public static CheeseApplication get() {
        return (CheeseApplication) Application.get();
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return LoginPage.class;
    }

    @Override
    public Session newSession(Request request, Response response) {
        return new CheeseSession(request);
    }

    public ModelLoader getModelLoader() {
        return modelLoader;
    }

    public SearchEngine getSearchEngine() {
        return searchEngine;
    }

    /*
    public List<Cheese> getEntries() {
        return Collections.unmodifiableList(cheeses);
    } */
}