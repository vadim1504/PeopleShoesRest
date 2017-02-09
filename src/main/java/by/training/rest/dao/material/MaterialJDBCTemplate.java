package by.training.rest.dao.material;

import by.training.rest.dao.AbstractDAO;
import by.training.rest.dao.user.UserMapper;
import by.training.rest.model.Material;
import by.training.rest.model.User;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MaterialJDBCTemplate extends AbstractDAO<Material,Integer> {

    public void create(Material entity) {
        jdbcTemplateObject.update(env.getProperty("createMaterial"), entity.getNameRu(), entity.getNameEu());
    }

    public Material getEntity(Integer id) {
        List<Material> materialList = jdbcTemplateObject.query(env.getProperty("getMaterial"), new Object[]{id}, new MaterialMapper());
        if(materialList.isEmpty()){
            return null;
        }else{
            return materialList.get(0);
        }
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
