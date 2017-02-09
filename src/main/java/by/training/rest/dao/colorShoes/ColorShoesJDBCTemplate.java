package by.training.rest.dao.colorShoes;

import by.training.rest.dao.AbstractDAO;
import by.training.rest.dao.user.UserMapper;
import by.training.rest.model.ColorShoes;
import by.training.rest.model.User;

import java.util.List;

public class ColorShoesJDBCTemplate extends AbstractDAO<ColorShoes,Integer> {

    public void create(ColorShoes entity) {
        jdbcTemplateObject.update(env.getProperty("createColorShoes"), entity.getIdColor(), entity.getIdShoes(),entity.getAmount());
    }

    public ColorShoes getEntity(Integer id) {
        List<ColorShoes> colorShoesList = jdbcTemplateObject.query(env.getProperty("getColorShoes"), new Object[]{id}, new ColorShoesMapper());
        if(colorShoesList.isEmpty()){
            return null;
        }else{
            return colorShoesList.get(0);
        }
    }

    public List<ColorShoes> getListEntity() {
        List<ColorShoes> colorShoes = jdbcTemplateObject.query(env.getProperty("getListColorShoes"),new ColorShoesMapper());
        return colorShoes;
    }

    public List<ColorShoes> getListEntityByColor(int idColor) {
        List<ColorShoes> colorShoes = jdbcTemplateObject.query(env.getProperty("getListColorShoesByColor"),new Object[]{idColor}, new ColorShoesMapper());
        return colorShoes;
    }

    public List<ColorShoes> getListEntityByShoes(int idShoes) {
        List<ColorShoes> colorShoes = jdbcTemplateObject.query(env.getProperty("getListColorShoesByShoes"),new Object[]{idShoes}, new ColorShoesMapper());
        return colorShoes;
    }

    public void delete(Integer id) {
        jdbcTemplateObject.update(env.getProperty("deleteColorShoes"),id);

    }

    public void update(Integer id, ColorShoes entity) {
        jdbcTemplateObject.update(env.getProperty("updateColorShoes"),entity.getIdColor(),entity.getIdShoes(),entity.getAmount(),id);
    }
}
