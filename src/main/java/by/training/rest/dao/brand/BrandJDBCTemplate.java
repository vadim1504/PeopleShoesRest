package by.training.rest.dao.brand;

import by.training.rest.dao.AbstractDAO;
import by.training.rest.dao.menCollection.MenCollectionMapper;
import by.training.rest.dao.shoes.ShoesJDBCTemplate;
import by.training.rest.model.Brand;
import by.training.rest.model.MenCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BrandJDBCTemplate extends AbstractDAO<Brand,Integer>{

    public void create(Brand entity) {
        jdbcTemplateObject.update(env.getProperty("createBrand"), entity.getName(), entity.getInfoRu(),entity.getInfoEu(),entity.getLogo());
    }

    public Brand getEntity(Integer id) {
        List<Brand> brands = jdbcTemplateObject.query(env.getProperty("getBrand"), new Object[]{id}, new BrandMapper());
        if(brands.isEmpty()){
            return null;
        }else{
            return brands.get(0);
        }
    }

    public List<Brand> getListEntity() {
        List<Brand> brands = jdbcTemplateObject.query(env.getProperty("getListBrand"),new BrandMapper());
        return brands;
    }

    public void delete(Integer id) {
        jdbcTemplateObject.update(env.getProperty("deleteBrand"),id);
    }

    public void update(Integer id, Brand entity) {
        jdbcTemplateObject.update(env.getProperty("updateBrand"),entity.getName(), entity.getInfoRu(),entity.getInfoEu(),entity.getLogo(),id);
    }
}
