package cache;

import cache.iface.IDataCache;
import dao.iface.CheeseDAO;
import domain.Cheese;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Iwan on 14.20.3
 */
public class CheeseCache implements CheeseDAO {

    private CheeseDAO cheeseDAO;

    private IDataCache dataCache;

    private List<Cheese> cheeses;
    private List<Cheese> deletedCheeses;

    public CheeseCache(IDataCache dataCache, CheeseDAO cheeseDAO) {
        this.dataCache = dataCache;
        this.cheeseDAO = cheeseDAO;
        cheeses = new LinkedList<>(cheeseDAO.getCheesesList());
        deletedCheeses = new LinkedList<>();
    }

    @Override
    public List<Cheese> getCheesesList() {
        return cheeses;
    }

    @Override
    public boolean exists(Cheese cheese) {
        for (Cheese current : cheeses) {
            if (current.equals(cheese)) return true;
        }
        return false;
    }

    @Override
    public Cheese getCheese(int id) {
        for (Cheese cheese : cheeses) {
            if (cheese.getId() == id)
                return cheese;
        }
        for (Cheese cheese : deletedCheeses) {
            if (cheese.getId() == id) {
                return cheese;
            }
        }
        return null;
    }

    @Override
    public void addCheese(Cheese cheese) {
        cheeses.add(cheese);
        cheeseDAO.addCheese(cheese);
    }

    @Override
    public void updateCheese(Cheese cheese) {
        cheeseDAO.updateCheese(cheese);
    }

    @Override
    public void safeDeleteCheese(Cheese cheese) {
        cheeses.remove(cheese);
        cheese.delete();
        deletedCheeses.add(cheese);

        cheeseDAO.safeDeleteCheese(cheese);
    }
}