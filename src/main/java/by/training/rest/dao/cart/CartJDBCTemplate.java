package by.training.rest.dao.cart;

import by.training.rest.dao.AbstractDAO;
import by.training.rest.dao.brand.BrandMapper;
import by.training.rest.model.Brand;
import by.training.rest.model.Cart;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CartJDBCTemplate extends AbstractDAO<Cart,Integer> {

    public void create(Cart entity) {
        jdbcTemplateObject.update(env.getProperty("createCart"), entity.getUsername(),entity.getIdShoes());
    }

    public Cart getEntity(Integer id) {
        List<Cart> carts = jdbcTemplateObject.query(env.getProperty("getCart"), new Object[]{id}, new CartMapper());
        if (carts.isEmpty()) {
            return null;
        } else {
            return carts.get(0);
        }
    }

    public List<Cart> getListEntity() {
        List<Cart> carts = jdbcTemplateObject.query(env.getProperty("getListCart"),new CartMapper());
        return carts;
    }

    public List<Cart> getListEntityByUser(String user) {
        List<Cart> carts = jdbcTemplateObject.query(env.getProperty("getListCartByUser"),new Object[]{user},new CartMapper());
        return carts;
    }

    public void delete(Integer id) {}

    public void delete(Integer id,String username) {
        jdbcTemplateObject.update(env.getProperty("deleteCart"),id,username);
    }

    public void update(Integer id, Cart entity) {
        jdbcTemplateObject.update(env.getProperty("updateCart"), entity.getUsername(),entity.getIdShoes(),id);
    }
}
