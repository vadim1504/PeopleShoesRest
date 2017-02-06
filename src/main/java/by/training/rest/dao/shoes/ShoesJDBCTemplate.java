package by.training.rest.dao.shoes;

import by.training.rest.dao.AbstractDAO;
import by.training.rest.model.Shoes;
import by.training.rest.model.price.Price;
import by.training.rest.soap.PriceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ShoesJDBCTemplate extends AbstractDAO<Shoes,Integer> {

    @Autowired
    public PriceClient priceClient;

    public void create(Shoes entity) {
        jdbcTemplateObject.update(env.getProperty("createShoes"), entity.getNameRu(), entity.getNameEu(),entity.getIdBrand(),entity.getIdMaterial(),entity.getAmount(),entity.getImage());
        priceClient.createPrice(entity.getPrice());
    }

    public Shoes getEntity(Integer id) {
        Shoes shoes = jdbcTemplateObject.queryForObject(env.getProperty("getShoes"), new Object[]{id}, new ShoesMapper());
        Price price = priceClient.getPrice(shoes.getId());
        shoes.setPrice(price);
        return shoes;
    }

    public List<Shoes> getListEntity() {
        List<Shoes> shoes = jdbcTemplateObject.query(env.getProperty("getListShoes"),new ShoesMapper());
        List<Price> prices = priceClient.getListPrice();
        for (Shoes s: shoes){
            for(Price p: prices)
                if(p.getIdShoes()==s.getId()){
                    s.setPrice(p);
                }
        }
        return shoes;
    }

    public List<Shoes> getListEntityByPrice(int min,int max) {
        List<Shoes> shoes = new ArrayList<Shoes>();
        List<Price> prices = priceClient.getListPriceByPrice(min,max);
        for (Price p : prices) {
            shoes.add(getEntity(p.getIdShoes()));
        }
        return shoes;
    }

    public List<Shoes> getListShoesByBrand(int idBrand){
        List<Shoes> shoes = jdbcTemplateObject.query(env.getProperty("getListShoesByBrand"),new Object[]{idBrand},new ShoesMapper());
        List<Price> prices = priceClient.getListPrice();
        for (Shoes s: shoes){
            for(Price p: prices)
                if(p.getIdShoes()==s.getId()){
                    s.setPrice(p);
                }
        }
        return shoes;
    }

    public List<Shoes> getListShoesByMaterial(int idMaterial){
        List<Shoes> shoes = jdbcTemplateObject.query(env.getProperty("getListShoesByMaterial"),new Object[]{idMaterial},new ShoesMapper());
        List<Price> prices = priceClient.getListPrice();
        for (Shoes s: shoes){
            for(Price p: prices)
                if(p.getIdShoes()==s.getId()){
                    s.setPrice(p);
                }
        }
        return shoes;
    }

    public void delete(Integer id) {
        jdbcTemplateObject.update(env.getProperty("deleteShoes"),id);
        priceClient.deletePrice(id);
    }

    public void update(Integer id, Shoes entity) {
        jdbcTemplateObject.update(env.getProperty("updateShoes"),entity.getNameRu(), entity.getNameEu(),entity.getIdBrand(),entity.getIdMaterial(),entity.getAmount(),entity.getImage(),id);
        priceClient.updatePrice(entity.getPrice());
    }


}
