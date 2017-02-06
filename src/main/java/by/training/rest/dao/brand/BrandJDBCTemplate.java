package by.training.rest.dao.brand;

import by.training.rest.dao.AbstractDAO;
import by.training.rest.dao.shoes.ShoesJDBCTemplate;
import by.training.rest.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BrandJDBCTemplate extends AbstractDAO<Brand,Integer>{

    @Autowired
    public ShoesJDBCTemplate shoesJDBCTemplate;

    public void create(Brand entity) {
        jdbcTemplateObject.update(env.getProperty("createBrand"), entity.getName(), entity.getInfoRu(),entity.getInfoEu(),entity.getLogo());
    }

    public Brand getEntity(Integer id) {
        Brand brand = jdbcTemplateObject.queryForObject(env.getProperty("getBrand"), new Object[]{id}, new BrandMapper());
        brand.setShoesList(shoesJDBCTemplate.getListShoesByBrand(id));
        return brand;
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
