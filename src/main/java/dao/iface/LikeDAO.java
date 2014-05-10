package dao.iface;

import domain.Address;
import domain.Cheese;

import java.util.Set;

/**
 * Created by IRuskevich on 10.05.2014
 */
public interface LikeDAO {

    Set<Address> getLikesList(Cheese cheese);

    void insertLike(Cheese cheese, Address address);
}
