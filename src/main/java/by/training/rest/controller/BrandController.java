package by.training.rest.controller;

import by.training.rest.dao.brand.BrandJDBCTemplate;
import by.training.rest.dao.shoes.ShoesJDBCTemplate;
import by.training.rest.model.Brand;
import by.training.rest.model.Shoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
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

    @GetMapping(params = {"id"})
    public Brand getBrand(@RequestParam(value = "id") int id){
        Brand brand = brandJDBCTemplate.getEntity(id);
        List<Shoes> shoes = shoesJDBCTemplate.getListShoesByBrand(id);
        brand.setShoesList(shoes);
        return brand;
    }

    @GetMapping()
    public List<Brand> getListBrand(){
        return brandJDBCTemplate.getListEntity();
    }

    @PostMapping()
    public void createBrand(@RequestBody Brand brand){
        brandJDBCTemplate.create(brand);
    }


    @DeleteMapping(params = {"id"})
    public void deleteBrand(@RequestParam(value = "id")  int id) {
        brandJDBCTemplate.delete(id);
    }

    @PutMapping(params = {"id"})
    public void updateBrand(@RequestBody Brand brand, @RequestParam(value = "id") int id) {
        brandJDBCTemplate.update(id,brand);
    }
}