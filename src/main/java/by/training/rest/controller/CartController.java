package by.training.rest.controller;

import by.training.rest.dao.cart.CartJDBCTemplate;
import by.training.rest.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Configuration
@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    public CartJDBCTemplate cartJDBCTemplate;

    @GetMapping(params = {"id"})
    public Cart getCart(@RequestParam(value = "id") int id){
        return cartJDBCTemplate.getEntity(id);
    }

    @GetMapping
    public List<Cart> getListCart(){
        return cartJDBCTemplate.getListEntity();
    }

    @PostMapping
    public void createCart(@RequestBody Cart cart){
        cartJDBCTemplate.create(cart);
    }

    @DeleteMapping(params = {"id"})
    public void deleteCart(@RequestParam(value = "id")  int id) {
        cartJDBCTemplate.delete(id);
    }

    @PutMapping(params = {"id"})
    public void updateCart(@RequestBody Cart cart, @RequestParam(value = "id") int id) {
        cartJDBCTemplate.update(id,cart);
    }
}