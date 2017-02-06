package by.training.rest.controller;

import by.training.rest.dao.color.ColorJDBCTemplate;
import by.training.rest.dao.colorShoes.ColorShoesJDBCTemplate;
import by.training.rest.dao.shoes.ShoesJDBCTemplate;
import by.training.rest.model.Color;
import by.training.rest.model.ColorShoes;
import by.training.rest.model.Shoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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

    @GetMapping(params = {"id"})
    public Color getColor(@RequestParam(value = "id") int id){
        Color color = colorJDBCTemplate.getEntity(id);
        List<ColorShoes> colorShoes = colorShoesJDBCTemplate.getListEntityByColor(id);
        List<Shoes> shoes = new ArrayList<Shoes>();
        for (ColorShoes i: colorShoes){
            shoes.add(shoesJDBCTemplate.getEntity(i.getIdShoes()));
        }
        color.setShoesList(shoes);
        return color;
    }

    @GetMapping
    public List<Color> getListColor(){
        return colorJDBCTemplate.getListEntity();
    }

    @PostMapping
    public void createColor(@RequestBody Color color){
        colorJDBCTemplate.create(color);
    }

    @DeleteMapping(params = {"id"})
    public void deleteColor(@RequestParam(value = "id")  int id) {
        colorJDBCTemplate.delete(id);
    }

    @PutMapping(params = {"id"})
    public void updateColor(@RequestBody Color color, @RequestParam(value = "id") int id) {
        colorJDBCTemplate.update(id,color);
    }
}