package by.training.rest.dao.material;

import by.training.rest.dao.AbstractDAO;
import by.training.rest.model.Material;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MaterialJDBCTemplate extends AbstractDAO<Material,Integer> {

    public void create(Material entity) {
        jdbcTemplateObject.update(env.getProperty("createMaterial"), entity.getNameRu(), entity.getNameEu());
    }

    public Material getEntity(Integer id) {
        Material material = jdbcTemplateObject.queryForObject(env.getProperty("getMaterial"), new Object[]{id}, new MaterialMapper());
        return material;
    }

    public List<Material> getListEntity() {
        List<Material> materials = jdbcTemplateObject.query(env.getProperty("getListMaterial"),new MaterialMapper());
        return materials;
    }

    public void delete(Integer id) {
        jdbcTemplateObject.update(env.getProperty("deleteMaterial"),id);
    }

    public void update(Integer id, Material entity) {
        jdbcTemplateObject.update(env.getProperty("updateMaterial"),entity.getNameRu(),entity.getNameEu(),id);

    }
}
