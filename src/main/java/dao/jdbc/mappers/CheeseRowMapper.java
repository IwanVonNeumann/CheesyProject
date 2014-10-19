package dao.jdbc.mappers;

import domain.Cheese;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Iwan on 19.10.2014
 */

public class CheeseRowMapper implements RowMapper<Cheese> {

    @Override
    public Cheese mapRow(ResultSet resultSet, int i) throws SQLException {
        Cheese cheese = new Cheese();
        cheese.setId(resultSet.getInt("CheeseID"));
        cheese.setName(resultSet.getString("CheeseName"));
        cheese.setDescription(resultSet.getString("Description"));
        cheese.setPrice(resultSet.getDouble("Price"));
        cheese.setDeleted(resultSet.getBoolean("Deleted"));
        return cheese;
    }
}
