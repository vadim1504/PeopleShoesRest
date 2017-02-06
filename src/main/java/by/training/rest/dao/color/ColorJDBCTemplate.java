package by.training.rest.dao.color;

import by.training.rest.dao.AbstractDAO;
import by.training.rest.model.Color;

import java.util.List;

public class ColorJDBCTemplate extends AbstractDAO<Color,Integer> {

    public void create(Color entity) {
        jdbcTemplateObject.update(env.getProperty("createColor"), entity.getNameRu(), entity.getNameEu(),entity.getImage());
    }

    public Color getEntity(Integer id) {
        Color color = jdbcTemplateObject.queryForObject(env.getProperty("getColor"), new Object[]{id}, new ColorMapper());
        return color;
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
