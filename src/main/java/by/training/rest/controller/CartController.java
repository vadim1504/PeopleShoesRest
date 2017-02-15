package by.training.rest.controller;

import by.training.rest.dao.cart.CartJDBCTemplate;
import by.training.rest.dao.shoes.ShoesJDBCTemplate;
import by.training.rest.model.Cart;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Configuration
@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    public CartJDBCTemplate cartJDBCTemplate;
    @Autowired
    public ShoesJDBCTemplate shoesJDBCTemplate;

    private final Logger logger = Logger.getLogger(CartController.class);

    @GetMapping(params = {"id"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Cart> getCart(@RequestParam(value = "id") int id){
        logger.info("Fetching Cart with id="+id);
        Cart cart = cartJDBCTemplate.getEntity(id);
        if (cart == null) {
            logger.warn("With cart id=" + id + " not found");
            return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Cart>> getListCart(Principal principal){
        logger.info("Fetching List cart");
        UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();
        List<Cart> carts = cartJDBCTemplate.getListEntityByUser(userDetails.getUsername());
        if (carts.isEmpty()||carts==null) {
            logger.warn("List cart not found");
            return new ResponseEntity<List<Cart>>(HttpStatus.NO_CONTENT);
        }
        for (Cart c : carts) {
            c.setShoes(shoesJDBCTemplate.getEntity(c.getIdShoes()));
        }
        return new ResponseEntity<List<Cart>>(carts, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Void> createCart(@RequestBody Cart cart){
        logger.info("Create cart");
        if (Cart.isEmptyFields(cart))
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        cartJDBCTemplate.create(cart);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity<Void> deleteCart(@RequestParam(value = "id")  int id,Principal principal) {
        logger.info("Delete cart with id="+id);
        UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();
        if (cartJDBCTemplate.getEntity(id) == null) {
            logger.warn("cart not found");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        cartJDBCTemplate.delete(id, userDetails.getUsername());
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(params = {"id"})
    public ResponseEntity<Void> updateCart(@RequestBody Cart cart, @RequestParam(value = "id") int id) {
        logger.info("Update cart with id="+id);
        if (Cart.isEmptyFields(cart))
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        if (cartJDBCTemplate.getEntity(id) == null) {
            logger.warn("cart not found");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        cartJDBCTemplate.update(id, cart);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}