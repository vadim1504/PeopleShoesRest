package by.training.rest.dao.collectionShoes;

import by.training.rest.model.CollectionShoes;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CollectionShoesMapper implements RowMapper<CollectionShoes> {

    public CollectionShoes mapRow(ResultSet resultSet, int i) throws SQLException {
        CollectionShoes collectionShoes = new CollectionShoes();
        collectionShoes.setId(resultSet.getInt("id"));
        collectionShoes.setIdCollection(resultSet.getInt("id_collection"));
        collectionShoes.setIdShoes(resultSet.getInt("id_shoes"));
        return collectionShoes;
    }
}