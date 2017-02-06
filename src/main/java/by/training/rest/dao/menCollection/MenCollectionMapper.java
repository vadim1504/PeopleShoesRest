package by.training.rest.dao.menCollection;

import by.training.rest.model.MenCollection;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MenCollectionMapper implements RowMapper<MenCollection> {

    public MenCollection mapRow(ResultSet resultSet, int i) throws SQLException {
        MenCollection menCollection = new MenCollection();
        menCollection.setId(resultSet.getInt("id"));
        menCollection.setCollectionNameRu(resultSet.getString("collection_name_RU"));
        menCollection.setCollectionNameEu(resultSet.getString("collection_name_EU"));
        menCollection.setAmount(resultSet.getInt("amount"));
        menCollection.setImage(resultSet.getString("image"));
        menCollection.setInfoEu(resultSet.getString("infoEu"));
        menCollection.setInfoRu(resultSet.getString("infoRu"));
        return menCollection;
    }
}
