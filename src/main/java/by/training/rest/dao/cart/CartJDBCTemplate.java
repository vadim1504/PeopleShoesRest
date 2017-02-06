package by.training.rest.dao.cart;

import by.training.rest.dao.AbstractDAO;
import by.training.rest.model.Cart;

import java.util.List;

public class CartJDBCTemplate extends AbstractDAO<Cart,Integer> {

    public void create(Cart entity) {
        jdbcTemplateObject.update(env.getProperty("createCart"), entity.getIdUser(),entity.getIdShoes());
    }

    public Cart getEntity(Integer id) {
        Cart cart = jdbcTemplateObject.queryForObject(env.getProperty("getCart"), new Object[]{id}, new CartMapper());
        return cart;
    }

    public List<Cart> getListEntity() {
        List<Cart> carts = jdbcTemplateObject.query(env.getProperty("getListCart"),new CartMapper());
        return carts;
    }

    public void delete(Integer id) {
        jdbcTemplateObject.update(env.getProperty("deleteCart"),id);
    }

    public void update(Integer id, Cart entity) {
        jdbcTemplateObject.update(env.getProperty("updateCart"), entity.getIdUser(),entity.getIdShoes(),id);
    }
}
