package by.training.rest.controller;

import by.training.rest.dao.cart.CartJDBCTemplate;
import by.training.rest.dao.shoes.ShoesJDBCTemplate;
import by.training.rest.model.Brand;
import by.training.rest.model.Cart;
import by.training.rest.model.Shoes;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@Configuration
@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    public CartJDBCTemplate cartJDBCTemplate;
    @Autowired
    public ShoesJDBCTemplate shoesJDBCTemplate;
    @Autowired
    public TokenStore tokenStore;

    private final Logger logger = Logger.getLogger(CartController.class);

    @GetMapping(params = {"id"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Cart> getCart(@RequestParam(value = "id") int id){
        logger.info("Fetching Cart with id="+id);
        try {
            Cart cart = cartJDBCTemplate.getEntity(id);
            if (cart == null) {
                logger.warn("With cart id=" + id + " not found");
                return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Cart>(cart, HttpStatus.OK);
        }catch(Exception e){
            logger.error("Exception getCart "+e);
            return new ResponseEntity<Cart>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    @GetMapping()
    public ResponseEntity<List<Cart>> getListCart(@RequestParam(value = "access_token") String  access_token){
        logger.info("Fetching List cart");
        try {
            UserDetails userDetails = (UserDetails) tokenStore.readAuthentication(access_token).getUserAuthentication().getPrincipal();
            List<Cart> carts = cartJDBCTemplate.getListEntityByUser(userDetails.getUsername());
            if (carts.isEmpty()) {
                logger.warn("List cart not found");
                return new ResponseEntity<List<Cart>>(HttpStatus.NO_CONTENT);
            }
            for (Cart c : carts) {
                c.setShoes(shoesJDBCTemplate.getEntity(c.getIdShoes()));
            }
            return new ResponseEntity<List<Cart>>(carts, HttpStatus.OK);
        }catch(Exception e) {
            logger.error("Exception getListCart " + e);
            return new ResponseEntity<List<Cart>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Void> createCart(@RequestBody Cart cart){
        logger.info("Create cart");
        try {
            cartJDBCTemplate.create(cart);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        }catch(Exception e) {
            logger.error("Exception createCart " + e);
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity<Void> deleteCart(@RequestParam(value = "id")  int id,@RequestParam(value = "access_token") String  access_token) {
        logger.info("Delete cart with id="+id);
        try {
            UserDetails userDetails = (UserDetails) tokenStore.readAuthentication(access_token).getUserAuthentication().getPrincipal();
            if (cartJDBCTemplate.getEntity(id) == null) {
                logger.warn("cart not found");
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }
            cartJDBCTemplate.delete(id, userDetails.getUsername());
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }catch(Exception e) {
            logger.error("Exception deleteCart " + e);
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(params = {"id"})
    public ResponseEntity<Void> updateCart(@RequestBody Cart cart, @RequestParam(value = "id") int id) {
        logger.info("Update cart with id="+id);
        try {
            if (cartJDBCTemplate.getEntity(id) == null) {
                logger.warn("cart not found");
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }
            cartJDBCTemplate.update(id, cart);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }catch(Exception e) {
            logger.error("Exception updateCart " + e);
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}