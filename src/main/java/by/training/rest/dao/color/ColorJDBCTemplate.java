package by.training.rest.dao.color;

import by.training.rest.dao.AbstractDAO;
import by.training.rest.dao.user.UserMapper;
import by.training.rest.model.Color;
import by.training.rest.model.User;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ColorJDBCTemplate extends AbstractDAO<Color,Integer> {

    public void create(Color entity) {
        jdbcTemplateObject.update(env.getProperty("createColor"), entity.getNameRu(), entity.getNameEu(),entity.getImage());
    }

    public Color getEntity(Integer id) {
        List<Color> colors = jdbcTemplateObject.query(env.getProperty("getColor"), new Object[]{id}, new ColorMapper());
        if(colors.isEmpty()){
            return null;
        }else{
            return colors.get(0);
        }
    }

    public List<Color> getListEntity() {
        List<Color> colors = jdbcTemplateObject.query(env.getProperty("getListColor"),new ColorMapper());
        return colors;
    }

    public void delete(Integer id) {
        jdbcTemplateObject.update(env.getProperty("deleteColor"),id);
    }

    public void update(Integer id, Color entity) {
        jdbcTemplateObject.update(env.getProperty("updateColor"),entity.getNameRu(),entity.getNameEu(),entity.getImage(),id);
    }
}
