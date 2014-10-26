package dao.jdbc.mappers;

import dao.iface.AddressDAO;
import domain.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Iwan on 26.10.2014
 */

public class CommentRowMapper implements RowMapper<Comment> {

    private AddressDAO addressDAO;

    public CommentRowMapper(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    @Override
    public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
        Comment comment = new Comment();
        comment.setId(resultSet.getLong("CommentID"));
        comment.setText(resultSet.getString("Text"));
        comment.setTime(resultSet.getTimestamp("Clock"));
        comment.setAddress(addressDAO.getAddress(resultSet.getLong("CustomerID")));
        return comment;
    }
}
