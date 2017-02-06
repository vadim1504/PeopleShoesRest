package by.training.rest.dao.shoes;

import by.training.rest.model.Shoes;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShoesMapper implements RowMapper<Shoes> {

    public Shoes mapRow(ResultSet resultSet, int i) throws SQLException {
        Shoes shoes = new Shoes();
        shoes.setId(resultSet.getInt("id"));
        shoes.setIdBrand(resultSet.getInt("id_brand"));
        shoes.setIdMaterial(resultSet.getInt("id_material"));
        shoes.setAmount(resultSet.getInt("amount"));
        shoes.setImage(resultSet.getString("image"));
        shoes.setNameRu(resultSet.getString("name_RU"));
        shoes.setNameEu(resultSet.getString("name_EU"));
        return shoes;
    }
}