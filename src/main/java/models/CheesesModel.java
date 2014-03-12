package models;

import dao.jdbc.JDBCCheeseDAO;
import domain.Cheese;
import org.apache.wicket.model.LoadableDetachableModel;

import java.util.List;

public class CheesesModel extends LoadableDetachableModel {

    private JDBCCheeseDAO dao;

    public CheesesModel(JDBCCheeseDAO dao) {
        super();
        this.dao = dao;
    }

    @Override
    protected Object load() {
        return getCheeses();
    }

    /*
    @Override
    protected void onAttach() {
        super.onAttach();
        System.out.println("CheesesModel attached;");
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        System.out.println("CheesesModel detached;\n");
    }
    */

    protected List<Cheese> getCheeses() {
        return dao.getCheesesList();
    }
}
