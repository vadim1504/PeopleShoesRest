package by.training.rest.controller;

import by.training.rest.dao.brand.BrandJDBCTemplate;
import by.training.rest.dao.collectionShoes.CollectionShoesJDBCTemplate;
import by.training.rest.dao.color.ColorJDBCTemplate;
import by.training.rest.dao.colorShoes.ColorShoesJDBCTemplate;
import by.training.rest.dao.material.MaterialJDBCTemplate;
import by.training.rest.dao.menCollection.MenCollectionJDBCTemplate;
import by.training.rest.dao.shoes.ShoesJDBCTemplate;
import by.training.rest.dao.size.SizeJDBCTemplate;
import by.training.rest.dao.sizeShoes.SizeShoesJDBCTemplate;
import by.training.rest.exception.RestPreconditions;
import by.training.rest.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@RestController
@RequestMapping(value = "/shoes")
class ShoesController {

    @Autowired
    public ShoesJDBCTemplate shoesJDBCTemplate;
    @Autowired
    public BrandJDBCTemplate brandJDBCTemplate;
    @Autowired
    public MaterialJDBCTemplate materialJDBCTemplate;
    @Autowired
    public ColorShoesJDBCTemplate colorShoesJDBCTemplate;
    @Autowired
    public ColorJDBCTemplate colorJDBCTemplate;
    @Autowired
    public CollectionShoesJDBCTemplate collectionShoesJDBCTemplate;
    @Autowired
    public MenCollectionJDBCTemplate menCollectionJDBCTemplate;
    @Autowired
    public SizeShoesJDBCTemplate sizeShoesJDBCTemplate;
    @Autowired
    public SizeJDBCTemplate sizeJDBCTemplate;

    @GetMapping(params = {"id"})
    public Shoes getShoes(@RequestParam(value = "id") int id){


        Shoes shoes = shoesJDBCTemplate.getEntity(id);

        shoes.setBrand(brandJDBCTemplate.getEntity(shoes.getIdBrand()));
        shoes.setMaterial(materialJDBCTemplate.getEntity(shoes.getIdMaterial()));
        List<ColorShoes> colorShoes = colorShoesJDBCTemplate.getListEntityByShoes(id);
        List<Color> colors = new ArrayList<Color>();
        for (ColorShoes i: colorShoes){
            colors.add(colorJDBCTemplate.getEntity(i.getIdColor()));
        }
        shoes.setColorList(colors);
        List<CollectionShoes> collectionShoes = collectionShoesJDBCTemplate.getListEntityByShoes(id);
        List<MenCollection> menCollections = new ArrayList<MenCollection>();
        for(CollectionShoes i: collectionShoes){
            menCollections.add(menCollectionJDBCTemplate.getEntity(i.getIdCollection()));
        }
        shoes.setMenCollections(menCollections);
        List<SizeShoes> sizeShoes = sizeShoesJDBCTemplate.getListEntityByShoes(id);
        List<Size> sizes = new ArrayList<Size>();
        for (SizeShoes i: sizeShoes){
            sizes.add(sizeJDBCTemplate.getEntity(i.getIdSize()));
        }
        shoes.setSizes(sizes);
        return shoes;
    }

    @GetMapping()
    public List<Shoes> getListShoes(){
        return shoesJDBCTemplate.getListEntity();
    }

    @GetMapping(params = {"minPrice","maxPrice"})
    public List<Shoes> getListShoesByPriceMinMax(@RequestParam(value = "minPrice")  int min,@RequestParam(value = "maxPrice")  int max){
        return shoesJDBCTemplate.getListEntityByPrice(min,max+1);
    }

    @GetMapping(params = {"minSize","maxSize"})
    public List<Shoes> getListShoesBySizeMinMax(@RequestParam(value = "minSize")  int min,@RequestParam(value = "maxSize")  int max){
        Set<Shoes> shoes = new HashSet<Shoes>();
        List<Size> sizes = sizeJDBCTemplate.getListEntityByMinMax(min,max);
        List<SizeShoes> sizeShoes = new ArrayList<SizeShoes>();
        for(Size s: sizes){
            sizeShoes.addAll(sizeShoesJDBCTemplate.getListEntityBySize(s.getId()));
        }
        for(SizeShoes s: sizeShoes){
            shoes.add(shoesJDBCTemplate.getEntity(s.getIdShoes()));
        }
        return new ArrayList<Shoes>(shoes);
    }

    @PostMapping()
    public void createShoes(@RequestBody Shoes shoes){

        shoesJDBCTemplate.create(shoes);
    }

    @DeleteMapping(params = {"id"})
    public void deleteShoes(@RequestParam(value = "id")  int id) {

        shoesJDBCTemplate.delete(id);
    }

    @PutMapping(params = {"id"})
    public void updateShoes(@RequestBody Shoes shoes, @RequestParam(value = "id") int id) {


        shoesJDBCTemplate.update(id,shoes);
    }

    @ResponseStatus( value = HttpStatus.BAD_REQUEST )
    public class BadRequestException extends RuntimeException{

    }
    @ResponseStatus( value = HttpStatus.NOT_FOUND )
    public class ResourceNotFoundException extends RuntimeException{

    }

}
