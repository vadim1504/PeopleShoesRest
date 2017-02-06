package by.training.rest.dao.size;

import by.training.rest.model.Size;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SizeMapper implements RowMapper<Size> {

    public Size mapRow(ResultSet resultSet, int i) throws SQLException {
        Size size = new Size();
        size.setId(resultSet.getInt("id"));
        size.setSizeEU(resultSet.getString("name_EU"));
        size.setSizeUS(resultSet.getString("name_US"));
        size.setSizeUK(resultSet.getString("name_UK"));
        return size;
    }
}