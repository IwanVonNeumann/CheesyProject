package dao.iface;

import domain.Cheese;

import java.util.List;

/**
 * Created by IRuskevich on 14.12.3.
 */
public interface CheeseDAO {

    List<Cheese> getCheesesList();

    Cheese getCheese(int id);

    void addCheese(Cheese cheese);

    void updateCheese(Cheese cheese);

    // void deleteCheese(Cheese cheese); // возможно, удалять по умлочанию стоит безопасно

    void safeDeleteCheese(Cheese cheese);
}