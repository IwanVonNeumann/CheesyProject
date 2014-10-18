package models;

import cache.iface.IDataCache;
import domain.Cheese;

import java.util.List;

public class CheesesLDModel extends AbstractLDModel {

    public CheesesLDModel(IDataCache dataCache) {
        super(dataCache);
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
        return dataCache.getCheesesList();
    }
}
