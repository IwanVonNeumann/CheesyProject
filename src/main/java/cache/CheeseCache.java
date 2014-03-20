package cache;

import dao.iface.CheeseDAO;
import domain.Cheese;

import java.util.List;

/**
 * Created by Iwan on 14.20.3.
 */
public class CheeseCache implements CheeseDAO {

    private CheeseDAO cheeseDAO;

    public CheeseCache(CheeseDAO cheeseDAO) {
        this.cheeseDAO = cheeseDAO;
    }

    @Override
    public List<Cheese> getCheesesList() {
        return cheeseDAO.getCheesesList();
    }

    @Override
    public Cheese getCheese(int id) {
        return cheeseDAO.getCheese(id);
    }

    @Override
    public void addCheese(Cheese cheese) {
        cheeseDAO.addCheese(cheese);
    }

    @Override
    public void updateCheese(Cheese cheese) {
        cheeseDAO.updateCheese(cheese);
    }

    @Override
    public void safeDeleteCheese(Cheese cheese) {
        cheeseDAO.safeDeleteCheese(cheese);
    }
}
