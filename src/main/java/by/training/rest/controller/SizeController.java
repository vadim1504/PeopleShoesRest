package by.training.rest.controller;

import by.training.rest.dao.shoes.ShoesJDBCTemplate;
import by.training.rest.dao.size.SizeJDBCTemplate;
import by.training.rest.dao.sizeShoes.SizeShoesJDBCTemplate;
import by.training.rest.model.Shoes;
import by.training.rest.model.Size;
import by.training.rest.model.SizeShoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RestController
@RequestMapping(value = "/size")
public class SizeController {

    @Autowired
    public SizeJDBCTemplate sizeJDBCTemplate;
    @Autowired
    public ShoesJDBCTemplate shoesJDBCTemplate;
    @Autowired
    public SizeShoesJDBCTemplate sizeShoesJDBCTemplate;

    @GetMapping(params = {"id"})
    public Size getSize(@RequestParam(value = "id") int id){
        Size size = sizeJDBCTemplate.getEntity(id);
        List<SizeShoes> sizeShoes = sizeShoesJDBCTemplate.getListEntityBySize(id);
        List<Shoes> shoes = new ArrayList<Shoes>();
        for (SizeShoes i: sizeShoes){
            shoes.add(shoesJDBCTemplate.getEntity(i.getIdShoes()));
        }
        size.setShoesList(shoes);
        return size;
    }

    @GetMapping()
    public List<Size> getListSize(){
        return sizeJDBCTemplate.getListEntity();
    }

    @PostMapping()
    public void createSize(@RequestBody Size size){
        sizeJDBCTemplate.create(size);
    }

    @DeleteMapping(params = {"id"})
    public void deleteSize(@RequestParam(value = "id")  int id) {
        sizeJDBCTemplate.delete(id);
    }

    @PutMapping(params = {"id"})
    public void updateSize(@RequestBody Size size, @RequestParam(value = "id") int id) {
        sizeJDBCTemplate.update(id,size);
    }
}