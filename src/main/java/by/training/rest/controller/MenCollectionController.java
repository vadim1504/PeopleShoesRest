package by.training.rest.controller;

import by.training.rest.dao.collectionShoes.CollectionShoesJDBCTemplate;
import by.training.rest.dao.menCollection.MenCollectionJDBCTemplate;
import by.training.rest.dao.shoes.ShoesJDBCTemplate;
import by.training.rest.model.CollectionShoes;
import by.training.rest.model.MenCollection;
import by.training.rest.model.Shoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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

    @GetMapping(params = {"id"})
    public MenCollection getMenCollection(@RequestParam(value = "id") int id){
        MenCollection menCollection = menCollectionJDBCTemplate.getEntity(id);
        List<CollectionShoes> collectionShoes = collectionShoesJDBCTemplate.getListEntityByCollection(id);
        List<Shoes> shoes = new ArrayList<Shoes>();
        for (CollectionShoes i: collectionShoes){
            shoes.add(shoesJDBCTemplate.getEntity(i.getIdShoes()));
        }
        menCollection.setShoesList(shoes);
        return menCollection;
    }

    @GetMapping()
    public List<MenCollection> getListMenCollections(){
        return menCollectionJDBCTemplate.getListEntity();
    }


    @PostMapping()
    public void createMenCollection(@RequestBody MenCollection menCollection){
        menCollectionJDBCTemplate.create(menCollection);
    }

    @DeleteMapping(params = {"id"})
    public void deleteMenCollection(@RequestParam(value = "id")  int id) {
        menCollectionJDBCTemplate.delete(id);
    }

    @PutMapping(params = {"id"})
    public void updateMenCollection(@RequestBody MenCollection menCollection, @RequestParam(value = "id") int id) {
        menCollectionJDBCTemplate.update(id,menCollection);
    }
}
