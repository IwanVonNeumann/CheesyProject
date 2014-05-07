package models;

import dao.iface.CheeseDAO;
import domain.Cheese;
import org.apache.wicket.model.LoadableDetachableModel;

import java.util.List;

public class CheesesModel extends LoadableDetachableModel {

    private CheeseDAO dao;

    public CheesesModel(CheeseDAO dao) {
        super();
        this.dao = dao;
    }

    @Override
    protected Object load() {
        //System.out.println("[Wicket] CheesesModel.load();");
        return getCheeses();
    }

    /*@Override
    protected void onAttach() {
        super.onAttach();
        System.out.println("[Wicket] CheesesModel.onAttach();");
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        System.out.println("[Wicket] CheesesModel.onDetach();");
    }*/

    protected List<Cheese> getCheeses() {
        return dao.getCheesesList();
    }
}
