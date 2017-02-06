package by.training.rest.dao.color;


import by.training.rest.model.Color;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ColorMapper implements RowMapper<Color> {

    public Color mapRow(ResultSet resultSet, int i) throws SQLException {
        Color color = new Color();
        color.setId(resultSet.getInt("id"));
        color.setNameRu(resultSet.getString("name_RU"));
        color.setNameEu(resultSet.getString("name_EU"));
        color.setImage(resultSet.getString("image"));
        return color;
    }
}