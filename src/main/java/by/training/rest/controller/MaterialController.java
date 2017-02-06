package by.training.rest.controller;

import by.training.rest.dao.material.MaterialJDBCTemplate;
import by.training.rest.dao.shoes.ShoesJDBCTemplate;
import by.training.rest.model.Material;
import by.training.rest.model.Shoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Configuration
@RestController
@RequestMapping(value = "/material")
public class MaterialController {

    @Autowired
    public MaterialJDBCTemplate materialJDBCTemplate;
    @Autowired
    public ShoesJDBCTemplate shoesJDBCTemplate;

    @GetMapping(params = {"id"})
    public Material getMaterial(@RequestParam(value = "id") int id){
        Material material = materialJDBCTemplate.getEntity(id);
        List<Shoes> shoes = shoesJDBCTemplate.getListShoesByMaterial(id);
        material.setShoesList(shoes);
        return material;
    }

    @GetMapping()
    public List<Material> getListMaterial(){
        return materialJDBCTemplate.getListEntity();
    }

    @PostMapping()
    public void createMaterial(@RequestBody Material material){
        materialJDBCTemplate.create(material);
    }

    @DeleteMapping(params = {"id"})
    public void deleteMaterial(@RequestParam(value = "id")  int id) {
        materialJDBCTemplate.delete(id);
    }

    @PutMapping(params = {"id"})
    public void updateMaterial(@RequestBody Material material, @RequestParam(value = "id") int id) {
        materialJDBCTemplate.update(id,material);
    }
}