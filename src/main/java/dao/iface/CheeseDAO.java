package dao.iface;

import domain.Cheese;
import search.SearchEngine;

import java.util.List;

/**
 * Created by IRuskevich on 14.12.3
 */
public interface CheeseDAO {

    List<Cheese> getCheesesList();

    boolean exists(Cheese cheese);

    Cheese getCheese(int id);

    void addCheese(Cheese cheese);

    void updateCheese(Cheese cheese);

    void safeDeleteCheese(Cheese cheese);

    List<Cheese> searchCheeseByName(String key);

    List<Cheese> searchCheeseByDescription(String key);
}