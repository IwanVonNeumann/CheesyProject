package dao.iface;

import domain.Cheese;

import java.util.List;

/**
 * Created by IRuskevich on 14.12.3.
 */
public interface CheeseDAO {

    List<Cheese> getCheesesList();

    Cheese getCheese(int id);

    void deleteCheese(Cheese cheese);

    //void safeDeleteCheese(Cheese cheese);

    void addCheese(Cheese cheese);

    void updateCheese(Cheese cheese);
}
