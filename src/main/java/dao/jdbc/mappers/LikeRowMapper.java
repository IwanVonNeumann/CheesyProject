package dao.jdbc.mappers;

import dao.iface.AddressDAO;
import dao.iface.CheeseDAO;
import domain.Like;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Iwan on 26.10.2014
 */

public class LikeRowMapper implements RowMapper<Like> { // not needed for now

    private CheeseDAO cheeseDAO;

    private AddressDAO addressDAO;

    public LikeRowMapper(CheeseDAO cheeseDAO, AddressDAO addressDAO) {
        this.cheeseDAO = cheeseDAO;
        this.addressDAO = addressDAO;
    }


    @Override
    public Like mapRow(ResultSet resultSet, int i) throws SQLException {
        Like like = new Like();
        like.setId(resultSet.getLong("LikeID"));
        like.setCheese(cheeseDAO.getCheese(resultSet.getLong("CheeseID")));
        like.setAddress(addressDAO.getAddress(resultSet.getLong("CustomerID")));
        return like;
    }
}
