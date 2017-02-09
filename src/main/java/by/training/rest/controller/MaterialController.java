package by.training.rest.controller;

import by.training.rest.dao.material.MaterialJDBCTemplate;
import by.training.rest.dao.shoes.ShoesJDBCTemplate;
import by.training.rest.model.Material;
import by.training.rest.model.Shoes;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final Logger logger = Logger.getLogger(MaterialController.class);

    @GetMapping(params = {"id"})
    public ResponseEntity<Material> getMaterial(@RequestParam(value = "id") int id){
        logger.info("Fetching material with id="+id);
        Material material = materialJDBCTemplate.getEntity(id);
        if(material==null){
            logger.warn("With material id="+id+" not found");
            return new ResponseEntity<Material>(HttpStatus.NOT_FOUND);
        }
        List<Shoes> shoes = shoesJDBCTemplate.getListShoesByMaterial(id);
        material.setShoesList(shoes);
        return new ResponseEntity<Material>(material,HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Material>> getListMaterial() {
        logger.info("Fetching List material");
        List<Material> materials = materialJDBCTemplate.getListEntity();
        if (materials.isEmpty()) {
            logger.warn("List Material not found");
            return new ResponseEntity<List<Material>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Material>>(materials, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> createMaterial(@RequestBody Material material){
        logger.info("Create color");
        materialJDBCTemplate.create(material);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity<Void> deleteMaterial(@RequestParam(value = "id")  int id) {
        logger.info("Delete material with id="+id);
        if (materialJDBCTemplate.getEntity(id) == null) {
            logger.warn("Material not found");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        materialJDBCTemplate.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(params = {"id"})
    public ResponseEntity<Void> updateMaterial(@RequestBody Material material, @RequestParam(value = "id") int id) {
        logger.info("Update material with id="+id);
        if (materialJDBCTemplate.getEntity(id) == null) {
            logger.warn("material not found");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        materialJDBCTemplate.update(id,material);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}