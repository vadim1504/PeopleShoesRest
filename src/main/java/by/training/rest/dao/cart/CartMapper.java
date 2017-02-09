package by.training.rest.dao.cart;

import by.training.rest.model.Cart;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartMapper implements RowMapper<Cart> {

    public Cart mapRow(ResultSet resultSet, int i) throws SQLException {
        Cart cart = new Cart();
        cart.setId(resultSet.getInt("id"));
        cart.setUsername(resultSet.getString("username"));
        cart.setIdShoes(resultSet.getInt("id_shoes"));
        return cart;
    }
}