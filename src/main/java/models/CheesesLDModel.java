package models;

import dao.iface.CheeseDAO;
import domain.Cheese;
import org.apache.wicket.model.LoadableDetachableModel;

import java.util.List;

public class CheesesLDModel extends LoadableDetachableModel {

    private CheeseDAO dao;

    public CheesesLDModel(CheeseDAO dao) {
        super();
        this.dao = dao;
    }

    @Override
    protected Object load() {
        //System.out.println("[Wicket] CheesesLDModel.load();");
        return getCheeses();
    }

    /*@Override
    protected void onAttach() {
        super.onAttach();
        System.out.println("[Wicket] CheesesLDModel.onAttach();");
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        System.out.println("[Wicket] CheesesLDModel.onDetach();");
    }*/

    protected List<Cheese> getCheeses() {
        return dao.getCheesesList();
    }
}
