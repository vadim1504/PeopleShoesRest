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
        try {
            jdbcTemplateObject.update(env.getProperty("createShoes"), entity.getNameRu(), entity.getNameEu(), entity.getIdBrand(), entity.getIdMaterial(), entity.getAmount(), entity.getImage());
            Price price = entity.getPrice();
            price.setIdShoes(getIdShoes(entity));
            priceClient.createPrice(price);
        }catch (Exception e){}// проверка или откат
    }

    public int getIdShoes(Shoes entity){
        return jdbcTemplateObject.queryForObject("select id from shoes where name_Ru=? and name_Eu=? and amount=? and id_brand=? and id_material=?", new Object[]{entity.getNameRu(), entity.getNameEu(), entity.getAmount(), entity.getIdBrand(), entity.getIdMaterial()}, Integer.class);
    }

    public Shoes getEntity(Integer id) {
        Shoes shoes;
        List<Shoes> shoesList = jdbcTemplateObject.query(env.getProperty("getShoes"), new Object[]{id}, new ShoesMapper());
        if(shoesList.isEmpty()){
            return null;
        }else{
            shoes = shoesList.get(0);
        }
        Price price = null;
        try {
            price = priceClient.getPrice(shoes.getId());
        }catch (Exception e){}
        shoes.setPrice(price);
        return shoes;
    }
    public List<Shoes> getListEntityNPage(int p,int s){
        List<Shoes> shoes = jdbcTemplateObject.query("SELECT * FROM shoes limit ? , ?",new Object[]{p*s,s},new ShoesMapper());
        for (Shoes shoes1 : shoes) {
            try {
                shoes1.setPrice(priceClient.getPrice(shoes1.getId()));
            }catch (Exception e){}
        }
        return shoes;
    }

    public List<Shoes> getListEntity() {
        List<Shoes> shoes = jdbcTemplateObject.query(env.getProperty("getListShoes"),new ShoesMapper());
        try {
            List<Price> prices = priceClient.getListPrice();
            for (Shoes s : shoes) {
                for (Price p : prices)
                    if (p.getIdShoes() == s.getId()) {
                        s.setPrice(p);
                    }
            }
        }catch (Exception e){}
        return shoes;
    }

    public List<Shoes> getListEntityByPrice(int min,int max) {
        List<Shoes> shoes = new ArrayList<Shoes>();
        try {
        List<Price> prices = priceClient.getListPriceByPrice(min,max);
        for (Price p : prices) {
            shoes.add(getEntity(p.getIdShoes()));
        }}catch (Exception e){}
        return shoes;
    }

    public List<Shoes> getListShoesByBrand(int idBrand){
        List<Shoes> shoes = jdbcTemplateObject.query(env.getProperty("getListShoesByBrand"),new Object[]{idBrand},new ShoesMapper());
            for (Shoes s : shoes) {
                try {
                s.setPrice(priceClient.getPrice(s.getId()));
                }catch (Exception e){}
            }
        return shoes;
    }

    public List<Shoes> getListShoesByMaterial(int idMaterial){
        List<Shoes> shoes = jdbcTemplateObject.query(env.getProperty("getListShoesByMaterial"),new Object[]{idMaterial},new ShoesMapper());
        for (Shoes s : shoes) {
            try {
                s.setPrice(priceClient.getPrice(s.getId()));
            }catch (Exception e){}
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
