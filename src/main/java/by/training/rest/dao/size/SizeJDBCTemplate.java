package by.training.rest.dao.size;

import by.training.rest.dao.AbstractDAO;
import by.training.rest.dao.user.UserMapper;
import by.training.rest.model.Size;
import by.training.rest.model.User;

import java.util.List;

public class SizeJDBCTemplate extends AbstractDAO<Size,Integer> {

    public void create(Size entity) {
        jdbcTemplateObject.update(env.getProperty("createSize"), entity.getSizeEU(), entity.getSizeUS(),entity.getSizeUK());
    }

    public Size getEntity(Integer id) {
        List<Size> sizeList = jdbcTemplateObject.query(env.getProperty("getSize"), new Object[]{id}, new SizeMapper());
        if(sizeList.isEmpty()){
            return null;
        }else{
            return sizeList.get(0);
        }
    }

    public List<Size> getListEntity() {
        List<Size> sizes = jdbcTemplateObject.query(env.getProperty("getListSize"),new SizeMapper());
        return sizes;
    }

    public List<Size> getListEntityByMinMax(int min,int max){
        List<Size> sizes = jdbcTemplateObject.query(env.getProperty("getListSizeByMinMax"),new Object[]{min,max},new SizeMapper());
        return sizes;
    }

    public void delete(Integer id) {
        jdbcTemplateObject.update(env.getProperty("deleteSize"),id);
    }

    public void update(Integer id, Size entity) {
        jdbcTemplateObject.update(env.getProperty("updateSize"),entity.getSizeEU(),entity.getSizeUS(),entity.getSizeUK(),id);
    }
}
