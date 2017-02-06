package by.training.rest.dao.sizeShoes;


import by.training.rest.model.SizeShoes;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SizeShoesMapper implements RowMapper<SizeShoes> {

    public SizeShoes mapRow(ResultSet resultSet, int i) throws SQLException {
        SizeShoes menCollection = new SizeShoes();
        menCollection.setId(resultSet.getInt("id"));
        menCollection.setIdShoes(resultSet.getInt("id_shoes"));
        menCollection.setIdSize(resultSet.getInt("id_size"));
        menCollection.setAmount(resultSet.getInt("amount"));
        return menCollection;
    }
}