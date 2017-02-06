package by.training.rest.dao.brand;

import by.training.rest.model.Brand;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandMapper implements RowMapper<Brand> {

    public Brand mapRow(ResultSet resultSet, int i) throws SQLException {
        Brand brand = new Brand();
        brand.setId(resultSet.getInt("id"));
        brand.setName(resultSet.getString("name"));
        brand.setInfoRu(resultSet.getString("info_RU"));
        brand.setInfoEu(resultSet.getString("info_EU"));
        brand.setLogo(resultSet.getString("logo"));
        return brand;
    }
}