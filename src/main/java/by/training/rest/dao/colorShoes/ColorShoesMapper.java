package by.training.rest.dao.colorShoes;

import by.training.rest.model.ColorShoes;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ColorShoesMapper implements RowMapper<ColorShoes> {

    public ColorShoes mapRow(ResultSet resultSet, int i) throws SQLException {
        ColorShoes colorShoes = new ColorShoes();
        colorShoes.setId(resultSet.getInt("id"));
        colorShoes.setIdColor(resultSet.getInt("id_color"));
        colorShoes.setIdShoes(resultSet.getInt("id_shoes"));
        colorShoes.setAmount(resultSet.getInt("amount"));
        return colorShoes;
    }
}