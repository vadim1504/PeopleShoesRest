package by.training.rest.dao.collectionShoes;

import by.training.rest.dao.AbstractDAO;
import by.training.rest.dao.sizeShoes.SizeShoesMapper;
import by.training.rest.model.CollectionShoes;
import by.training.rest.model.SizeShoes;

import java.util.List;

public class CollectionShoesJDBCTemplate extends AbstractDAO<CollectionShoes,Integer> {

    public void create(CollectionShoes entity) {
        jdbcTemplateObject.update(env.getProperty("createCollectionShoes"), entity.getIdCollection(), entity.getIdShoes());

    }

    public CollectionShoes getEntity(Integer id) {
        List<CollectionShoes> collectionShoesList = jdbcTemplateObject.query(env.getProperty("getCollectionShoes"), new Object[]{id}, new CollectionShoesMapper());
        if(collectionShoesList.isEmpty()){
            return null;
        }else{
            return collectionShoesList.get(0);
        }
    }

    public List<CollectionShoes> getListEntity() {
        List<CollectionShoes> collectionShoes = jdbcTemplateObject.query(env.getProperty("getListCollectionShoes"),new CollectionShoesMapper());
        return collectionShoes;
    }

    public List<CollectionShoes> getListEntityByCollection(int idCollection) {
        List<CollectionShoes> collectionShoes = jdbcTemplateObject.query(env.getProperty("getListCollectionShoesByCollection"),new Object[]{idCollection}, new CollectionShoesMapper());
        return collectionShoes;
    }

    public List<CollectionShoes> getListEntityByShoes(int idShoes) {
        List<CollectionShoes> collectionShoes = jdbcTemplateObject.query(env.getProperty("getListCollectionShoesByShoes"),new Object[]{idShoes}, new CollectionShoesMapper());
        return collectionShoes;
    }

    public void delete(Integer id) {
        jdbcTemplateObject.update(env.getProperty("deleteCollectionShoes"),id);
    }

    public void update(Integer id, CollectionShoes entity) {
        jdbcTemplateObject.update(env.getProperty("updateCollectionShoes"),entity.getIdCollection(),entity.getIdShoes(),id);

    }
}
