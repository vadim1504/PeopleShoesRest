package by.training.rest.dao.menCollection;

import by.training.rest.dao.AbstractDAO;
import by.training.rest.model.MenCollection;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MenCollectionJDBCTemplate extends AbstractDAO<MenCollection,Integer> {

    public void create(MenCollection entity) {
        jdbcTemplateObject.update(env.getProperty("createMenCollection"), entity.getCollectionNameRu(), entity.getCollectionNameEu(),entity.getAmount(),entity.getImage(),entity.getInfoEu(),entity.getInfoRu());
    }

    public MenCollection getEntity(Integer id) {
        MenCollection menCollection = jdbcTemplateObject.queryForObject(env.getProperty("getMenCollection"), new Object[]{id}, new MenCollectionMapper());
        return menCollection;
    }

    public List<MenCollection> getListEntity() {
        List<MenCollection> menCollections = jdbcTemplateObject.query(env.getProperty("getListMenCollection"),new MenCollectionMapper());
        return menCollections;
    }

    public void delete(Integer id) {
        jdbcTemplateObject.update(env.getProperty("deleteMenCollection"),id);
    }

    public void update(Integer id, MenCollection entity) {
        jdbcTemplateObject.update(env.getProperty("updateMenCollection"),entity.getCollectionNameRu(),entity.getCollectionNameEu(),entity.getAmount(),entity.getImage(),entity.getInfoEu(),entity.getInfoRu(),id);
    }
}
