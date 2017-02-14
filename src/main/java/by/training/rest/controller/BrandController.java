package by.training.rest.controller;

import by.training.rest.dao.brand.BrandJDBCTemplate;
import by.training.rest.dao.shoes.ShoesJDBCTemplate;
import by.training.rest.model.Brand;
import by.training.rest.model.Shoes;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Configuration
@RestController
@RequestMapping(value = "/brand")
public class BrandController {

    @Autowired
    public BrandJDBCTemplate brandJDBCTemplate;
    @Autowired
    public ShoesJDBCTemplate shoesJDBCTemplate;

    private final Logger logger = Logger.getLogger(BrandController.class);

    @GetMapping(params = {"id"},produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Brand> getBrand(@RequestParam(value = "id") int id){
        logger.info("Fetching Brand with id="+id);
        if(id<1){
            logger.warn("Bad request");
            return new ResponseEntity<Brand>(HttpStatus.BAD_REQUEST);
        }
        Brand brand = brandJDBCTemplate.getEntity(id);
        if (brand == null) {
            logger.warn("With brand id=" + id + " not found");
            return new ResponseEntity<Brand>(HttpStatus.NOT_FOUND);
        }
        List<Shoes> shoes = shoesJDBCTemplate.getListShoesByBrand(id);
        brand.setShoesList(shoes);
        return new ResponseEntity<Brand>(brand,HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Brand>> getListBrand(){
        logger.info("Fetching List rand");
        List<Brand> brands = brandJDBCTemplate.getListEntity();
        if(brands.isEmpty()||brands==null){
            logger.warn("List Brand not found");
            return new ResponseEntity<List<Brand>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Brand>>(brands, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> createBrand(@RequestBody Brand brand) {
        logger.info("Create brand");
        brandJDBCTemplate.create(brand);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity<Void> deleteBrand(@RequestParam(value = "id")  int id) {
        logger.info("Delete brand with id="+id);
        if (brandJDBCTemplate.getEntity(id) == null) {
            logger.warn("brand not found");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        brandJDBCTemplate.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(params = {"id"})
    public ResponseEntity<Void> updateBrand(@RequestBody Brand brand, @RequestParam(value = "id") int id) {
        logger.info("Update brand with id="+id);
        if (brandJDBCTemplate.getEntity(id) == null)
        {
            logger.warn("Brand not found");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        brandJDBCTemplate.update(id, brand);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}