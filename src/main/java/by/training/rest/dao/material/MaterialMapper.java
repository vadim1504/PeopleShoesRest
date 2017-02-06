package by.training.rest.dao.material;

import by.training.rest.model.Material;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialMapper implements RowMapper<Material> {

    public Material mapRow(ResultSet resultSet, int i) throws SQLException {
        Material material = new Material();
        material.setId(resultSet.getInt("id"));
        material.setNameRu(resultSet.getString("name_RU"));
        material.setNameEu(resultSet.getString("name_EU"));
        return material;
    }
}