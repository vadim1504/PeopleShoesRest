package by.training.rest.dao.sizeShoes;

import by.training.rest.dao.AbstractDAO;
import by.training.rest.model.SizeShoes;

import java.util.List;

public class SizeShoesJDBCTemplate extends AbstractDAO<SizeShoes,Integer>{

    public void create(SizeShoes entity) {
        jdbcTemplateObject.update(env.getProperty("createSizeShoes"), entity.getIdSize(), entity.getIdShoes(),entity.getAmount());
    }

    public SizeShoes getEntity(Integer id) {
        SizeShoes sizeShoes = jdbcTemplateObject.queryForObject(env.getProperty("getSizeShoes"), new Object[]{id}, new SizeShoesMapper());
        return sizeShoes;
    }

    public List<SizeShoes> getListEntity() {
        List<SizeShoes> sizeShoes = jdbcTemplateObject.query(env.getProperty("getListSizeShoes"),new SizeShoesMapper());
        return sizeShoes;
    }

    public List<SizeShoes> getListEntityByShoes(int idShoes) {
        List<SizeShoes> sizeShoes = jdbcTemplateObject.query(env.getProperty("getListEntityByShoes"),new Object[]{idShoes},new SizeShoesMapper());
        return sizeShoes;
    }

    public List<SizeShoes> getListEntityBySize(int idSize) {
        List<SizeShoes> sizeShoes = jdbcTemplateObject.query(env.getProperty("getListEntityBySize"),new Object[]{idSize},new SizeShoesMapper());
        return sizeShoes;
    }

    public void delete(Integer id) {
        jdbcTemplateObject.update(env.getProperty("deleteSizeShoes"),id);

    }

    public void update(Integer id, SizeShoes entity) {
        jdbcTemplateObject.update(env.getProperty("updateSizeShoes"),entity.getIdSize(),entity.getIdShoes(),entity.getAmount(),id);
    }
}
