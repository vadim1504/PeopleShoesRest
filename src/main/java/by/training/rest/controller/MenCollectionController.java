package by.training.rest.controller;

import by.training.rest.dao.collectionShoes.CollectionShoesJDBCTemplate;
import by.training.rest.dao.menCollection.MenCollectionJDBCTemplate;
import by.training.rest.dao.shoes.ShoesJDBCTemplate;
import by.training.rest.model.CollectionShoes;
import by.training.rest.model.MenCollection;
import by.training.rest.model.Shoes;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RestController
@RequestMapping(value = "/menCollection")
public class MenCollectionController {

    @Autowired
    public MenCollectionJDBCTemplate menCollectionJDBCTemplate;
    @Autowired
    public CollectionShoesJDBCTemplate collectionShoesJDBCTemplate;
    @Autowired
    public ShoesJDBCTemplate shoesJDBCTemplate;
    private final Logger logger = Logger.getLogger(MenCollectionController.class);

    @GetMapping(params = {"id"})
    public ResponseEntity<MenCollection> getMenCollection(@RequestParam(value = "id") int id){
        logger.info("Fetching menCollection with id="+id);
        MenCollection menCollection = menCollectionJDBCTemplate.getEntity(id);
        if(menCollection==null){
            logger.warn("With collection id="+id+" not found");
            return new ResponseEntity<MenCollection>(HttpStatus.NOT_FOUND);
        }
        List<CollectionShoes> collectionShoes = collectionShoesJDBCTemplate.getListEntityByCollection(id);
        List<Shoes> shoes = new ArrayList<Shoes>();
        for (CollectionShoes i: collectionShoes){
            shoes.add(shoesJDBCTemplate.getEntity(i.getIdShoes()));
        }
        menCollection.setShoesList(shoes);
        return new ResponseEntity<MenCollection>(menCollection,HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<MenCollection>> getListMenCollections(){
        logger.info("Fetching List menCollection");
        List<MenCollection> menCollections = menCollectionJDBCTemplate.getListEntity();
        if (menCollections.isEmpty()) {
            logger.warn("List Material not found");
            return new ResponseEntity<List<MenCollection>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<MenCollection>>(menCollections, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<Void> createMenCollection(@RequestBody MenCollection menCollection){
        logger.info("Create collection");
        menCollectionJDBCTemplate.create(menCollection);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity<Void> deleteMenCollection(@RequestParam(value = "id")  int id) {
        logger.info("Delete collection with id="+id);
        if (menCollectionJDBCTemplate.getEntity(id) == null) {
            logger.warn("collection not found");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        menCollectionJDBCTemplate.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(params = {"id"})
    public ResponseEntity<Void> updateMenCollection(@RequestBody MenCollection menCollection, @RequestParam(value = "id") int id) {
        logger.info("Update collection with id="+id);
        if (menCollectionJDBCTemplate.getEntity(id) == null) {
            logger.warn("collection not found");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        menCollectionJDBCTemplate.update(id,menCollection);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }
}
