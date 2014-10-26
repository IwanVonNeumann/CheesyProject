package dao.iface;

import domain.Address;
import domain.Cheese;
import domain.Like;

import java.util.List;

/**
 * Created by IRuskevich on 10.05.2014
 */

public interface LikeDAO {

    List<Address> getLikesList(Cheese cheese);

    boolean exists(Like like);

    void insertLike(Like like);
}
