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
import by.training.rest.model.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    private final Logger logger = Logger.getLogger(ShoesController.class);

    @GetMapping(params = {"id"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Shoes> getShoes(@RequestParam(value = "id") int id){
        logger.info("Fetching Shoes with id="+id);
        Shoes shoes = shoesJDBCTemplate.getEntity(id);
        if(shoes==null){
            logger.warn("With Shoes id="+id+" not found");
            return new ResponseEntity<Shoes>(HttpStatus.NOT_FOUND);
        }
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
        return new ResponseEntity<Shoes>(shoes,HttpStatus.OK);
    }

    @GetMapping(params = {"page","size"})
    public ResponseEntity<List<Shoes>> getListShoesByNpage(@RequestParam(value="page") int p,@RequestParam(value="size") int s){
        List<Shoes> shoes = shoesJDBCTemplate.getListEntityNPage(p,s);
        if(shoes.isEmpty()){
            logger.warn("List Shoes not found");
            return new ResponseEntity<List<Shoes>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Shoes>>(shoes, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Shoes>> getListShoes(){
        logger.info("Fetching List Shoes");
        List<Shoes> shoes = shoesJDBCTemplate.getListEntity();
        if(shoes.isEmpty()){
            logger.warn("List Shoes not found");
            return new ResponseEntity<List<Shoes>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Shoes>>(shoes, HttpStatus.OK);
    }

    @GetMapping(params = {"minPrice","maxPrice"})
    public ResponseEntity<List<Shoes>> getListShoesByPriceMinMax(@RequestParam(value = "minPrice")  int min,@RequestParam(value = "maxPrice")  int max){
        logger.info("Fetching List Shoes with min,max price");
        List<Shoes> shoes = shoesJDBCTemplate.getListEntityByPrice(min,max+1);
        if(shoes.isEmpty()){
            logger.warn("List Shoes not found");
            return new ResponseEntity<List<Shoes>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Shoes>>(shoes, HttpStatus.OK);
    }

    @GetMapping(params = {"minSize","maxSize"})
    public ResponseEntity<List<Shoes>> getListShoesBySizeMinMax(@RequestParam(value = "minSize")  int min,@RequestParam(value = "maxSize")  int max){
        logger.info("Fetching List Shoes with min,max size");
        Set<Shoes> shoes = new HashSet<Shoes>();
        List<Size> sizes = sizeJDBCTemplate.getListEntityByMinMax(min,max);
        if(sizes.isEmpty()){
            logger.warn("List Shoes not found");
            return new ResponseEntity<List<Shoes>>(HttpStatus.NO_CONTENT);
        }
        List<SizeShoes> sizeShoes = new ArrayList<SizeShoes>();
        for(Size s: sizes){
            sizeShoes.addAll(sizeShoesJDBCTemplate.getListEntityBySize(s.getId()));
        }
        for(SizeShoes s: sizeShoes){
            shoes.add(shoesJDBCTemplate.getEntity(s.getIdShoes()));
        }
        if(shoes.isEmpty()){
            logger.warn("List Shoes not found");
            return new ResponseEntity<List<Shoes>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Shoes>>(new ArrayList<Shoes>(shoes),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> createShoes(@RequestBody Shoes shoes){
        logger.info("Create shoes");
        if(brandJDBCTemplate.getEntity(shoes.getIdBrand())==null)
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        if(materialJDBCTemplate.getEntity(shoes.getIdMaterial())==null)
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        if(Shoes.isEmptyFields(shoes))
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        shoesJDBCTemplate.create(shoes);
        int id = shoesJDBCTemplate.getIdShoes(shoes);
        if(shoes.getColorListId()!=null) {
            for (int i : shoes.getColorListId()) {
                colorShoesJDBCTemplate.create(new ColorShoes(i, id, 1));
            }
        }
        if(shoes.getMenCollectionsId()!=null) {
            for (int i : shoes.getMenCollectionsId()) {
                collectionShoesJDBCTemplate.create(new CollectionShoes(i, id));
            }
        }
        if (shoes.getSizesId()!=null) {
            for (int i : shoes.getSizesId()) {
                sizeShoesJDBCTemplate.create(new SizeShoes(i, id, 1));
            }
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity<Void> deleteShoes(@RequestParam(value = "id")  int id) {
        logger.info("Delete shoes with id="+id);
        Shoes shoes = shoesJDBCTemplate.getEntity(id);
        if (shoes == null) {
            logger.warn("Shoes not found");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        shoesJDBCTemplate.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(params = {"id"})
    public ResponseEntity<Void> updateShoes(@RequestBody Shoes shoes, @RequestParam(value = "id") int id) {
        logger.info("Update shoes with id="+id);
        if (shoesJDBCTemplate.getEntity(id) == null) {
            logger.warn("Shoes not found");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        if(brandJDBCTemplate.getEntity(shoes.getIdBrand())==null)
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        if(materialJDBCTemplate.getEntity(shoes.getIdMaterial())==null)
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        if(Shoes.isEmptyFields(shoes))
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        if(shoes.getColorListId()!=null||shoes.getColorListId().isEmpty()) {
            List<ColorShoes> colorShoes = colorShoesJDBCTemplate.getListEntityByShoes(id);
            if(colorShoes.size()> shoes.getColorListId().size()){
                for(int i=0;i<colorShoes.size()- shoes.getColorListId().size();i++){
                    colorShoesJDBCTemplate.delete(colorShoes.get(i).getId());
                    colorShoes.remove(i);
                }
            }
            int y=0;
            for (int i : shoes.getColorListId()) {
                if(colorShoes.get(y)==null){
                    colorShoesJDBCTemplate.create(new ColorShoes(i,id,1));
                }else {
                    int idColorShoes = colorShoes.get(y).getId();
                    colorShoesJDBCTemplate.update(idColorShoes, new ColorShoes(i, id, 1));
                }
                y++;
            }
        }
        if(shoes.getMenCollectionsId()!=null||shoes.getMenCollectionsId().isEmpty()) {
            List<CollectionShoes> collectionShoes = collectionShoesJDBCTemplate.getListEntityByShoes(id);
            if(collectionShoes.size()> shoes.getMenCollectionsId().size()){
                for(int i=0;i<collectionShoes.size()- shoes.getMenCollectionsId().size();i++){
                    collectionShoesJDBCTemplate.delete(collectionShoes.get(i).getId());
                    collectionShoes.remove(i);
                }
            }
            int y=0;
            for (int i : shoes.getMenCollectionsId()) {
                if(collectionShoes.get(y)==null){
                    collectionShoesJDBCTemplate.create(new CollectionShoes(i,id));
                }else {
                    int idCollectionShoes = collectionShoes.get(y).getId();
                    collectionShoesJDBCTemplate.update(idCollectionShoes, new CollectionShoes(i, id));
                }
                y++;
            }
        }
        if(shoes.getSizesId()!=null||shoes.getSizesId().isEmpty()) {
            List<SizeShoes> sizeShoes = sizeShoesJDBCTemplate.getListEntityByShoes(id);
            if(sizeShoes.size()> shoes.getSizesId().size()){
                for(int i=0;i<sizeShoes.size()- shoes.getSizesId().size();i++){
                    sizeShoesJDBCTemplate.delete(sizeShoes.get(i).getId());
                    sizeShoes.remove(i);
                }
            }
            int y=0;
            for (int i : shoes.getSizesId()) {
                if(sizeShoes.get(y)==null){
                    sizeShoesJDBCTemplate.create(new SizeShoes(i,id,1));
                }else {
                    int idSizeShoes = sizeShoes.get(y).getId();
                    sizeShoesJDBCTemplate.update(idSizeShoes, new SizeShoes(i, id, 1));
                }
                y++;
            }
        }
        shoesJDBCTemplate.update(id,shoes);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
