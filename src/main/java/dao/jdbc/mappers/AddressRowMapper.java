package dao.jdbc.mappers;

import domain.Address;
import domain.Title;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Iwan on 19.10.2014
 */

public class AddressRowMapper implements RowMapper<Address> {

    @Override
    public Address mapRow(ResultSet resultSet, int i) throws SQLException {

        Address address = new Address(
                Title.valueOf(resultSet.getString("Title")),
                resultSet.getString("CustomerName"),
                resultSet.getString("Street"),
                resultSet.getString("City"),
                resultSet.getInt("ZipCode"),
                resultSet.getInt("CustomerID"),
                resultSet.getBytes("PasswordHash"),
                resultSet.getBoolean("deleted"));
        return address;
    }
}
