package by.training.rest.controller;

import by.training.rest.dao.color.ColorJDBCTemplate;
import by.training.rest.dao.colorShoes.ColorShoesJDBCTemplate;
import by.training.rest.dao.shoes.ShoesJDBCTemplate;
import by.training.rest.model.Cart;
import by.training.rest.model.Color;
import by.training.rest.model.ColorShoes;
import by.training.rest.model.Shoes;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RestController
@RequestMapping(value = "/color")
public class ColorController {

    @Autowired
    public ColorJDBCTemplate colorJDBCTemplate;
    @Autowired
    public ColorShoesJDBCTemplate colorShoesJDBCTemplate;
    @Autowired
    public ShoesJDBCTemplate shoesJDBCTemplate;

    private final Logger logger = Logger.getLogger(ColorController.class);

    @GetMapping(params = {"id"})
    public ResponseEntity<Color> getColor(@RequestParam(value = "id") int id){
        logger.info("Fetching color with id="+id);
        try {
            Color color = colorJDBCTemplate.getEntity(id);
            if (color == null) {
                logger.warn("With color id=" + id + " not found");
                return new ResponseEntity<Color>(HttpStatus.NOT_FOUND);
            }
            List<ColorShoes> colorShoes = colorShoesJDBCTemplate.getListEntityByColor(id);
            List<Shoes> shoes = new ArrayList<Shoes>();
            for (ColorShoes i : colorShoes) {
                shoes.add(shoesJDBCTemplate.getEntity(i.getIdShoes()));
            }
            color.setShoesList(shoes);
            return new ResponseEntity<Color>(color, HttpStatus.OK);
        }catch(Exception e) {
            logger.error("Exception getColor " + e);
            return new ResponseEntity<Color>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Color>> getListColor(){
        logger.info("Fetching List color");
        try {
            List<Color> colors = colorJDBCTemplate.getListEntity();
            if (colors.isEmpty()) {
                logger.warn("List color not found");
                return new ResponseEntity<List<Color>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Color>>(colors, HttpStatus.OK);
        }catch(Exception e) {
            logger.error("Exception getListColor " + e);
            return new ResponseEntity<List<Color>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Void>  createColor(@RequestBody Color color){
        logger.info("Create color");
        try {
            colorJDBCTemplate.create(color);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        }catch(Exception e) {
            logger.error("Exception createColor " + e);
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity<Void> deleteColor(@RequestParam(value = "id")  int id) {
        logger.info("Delete color with id="+id);
        try {
            if (colorJDBCTemplate.getEntity(id) == null) {
                logger.warn("color not found");
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }
            colorJDBCTemplate.delete(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }catch(Exception e) {
            logger.error("Exception deleteColor " + e);
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(params = {"id"})
    public ResponseEntity<Void> updateColor(@RequestBody Color color, @RequestParam(value = "id") int id) {
        logger.info("Update color with id="+id);
        try {
            if (colorJDBCTemplate.getEntity(id) == null) {
                logger.warn("color not found");
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }
            colorJDBCTemplate.update(id, color);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }catch(Exception e) {
            logger.error("Exception updateColor " + e);
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}