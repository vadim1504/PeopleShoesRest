package by.training.rest.controller;

import by.training.rest.dao.shoes.ShoesJDBCTemplate;
import by.training.rest.dao.size.SizeJDBCTemplate;
import by.training.rest.dao.sizeShoes.SizeShoesJDBCTemplate;
import by.training.rest.model.Shoes;
import by.training.rest.model.Size;
import by.training.rest.model.SizeShoes;
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
@RequestMapping(value = "/size")
public class SizeController {

    @Autowired
    public SizeJDBCTemplate sizeJDBCTemplate;
    @Autowired
    public ShoesJDBCTemplate shoesJDBCTemplate;
    @Autowired
    public SizeShoesJDBCTemplate sizeShoesJDBCTemplate;

    private final Logger logger = Logger.getLogger(SizeController.class);

    @GetMapping(params = {"id"})
    public ResponseEntity<Size> getSize(@RequestParam(value = "id") int id){
        logger.info("Fetching Size with id="+id);
        Size size = sizeJDBCTemplate.getEntity(id);
        if(size==null){
            logger.warn("With Size id="+id+" not found");
            return new ResponseEntity<Size>(HttpStatus.NOT_FOUND);
        }
        List<SizeShoes> sizeShoes = sizeShoesJDBCTemplate.getListEntityBySize(id);
        List<Shoes> shoes = new ArrayList<Shoes>();
        for (SizeShoes i: sizeShoes){
            shoes.add(shoesJDBCTemplate.getEntity(i.getIdShoes()));
        }
        size.setShoesList(shoes);
        return new ResponseEntity<Size>(size,HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Size>> getListSize(){
        logger.info("Fetching List size");
        List<Size> sizes =sizeJDBCTemplate.getListEntity();
        if(sizes.isEmpty()){
            logger.warn("List size not found");
            return new ResponseEntity<List<Size>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Size>>(sizes, HttpStatus.OK);

    }

    @PostMapping()
    public ResponseEntity<Void> createSize(@RequestBody Size size) {
        logger.info("Create size");
        sizeJDBCTemplate.create(size);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity<Void> deleteSize(@RequestParam(value = "id")  int id) {
        if (sizeJDBCTemplate.getEntity(id) == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        sizeJDBCTemplate.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(params = {"id"})
    public ResponseEntity<Void> updateSize(@RequestBody Size size, @RequestParam(value = "id") int id) {
        if (sizeJDBCTemplate.getEntity(id)==null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        sizeJDBCTemplate.update(id,size);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}