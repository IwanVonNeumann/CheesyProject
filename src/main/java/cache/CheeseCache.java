package cache;

import dao.iface.CheeseDAO;
import domain.Cheese;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Iwan on 14.20.3
 */
public class CheeseCache implements CheeseDAO {

    private CheeseDAO cheeseDAO;

    private List<Cheese> cheeses;
    private List<Cheese> deletedCheeses;

    public CheeseCache(CheeseDAO cheeseDAO) {
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
    public Cheese getCheese(long id) {
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

    @Override
    public List<Cheese> searchCheeseByName(String key) {
        return cheeseDAO.searchCheeseByName(key);
    }

    @Override
    public List<Cheese> searchCheeseByDescription(String key) {
        return cheeseDAO.searchCheeseByDescription(key);
    }
}