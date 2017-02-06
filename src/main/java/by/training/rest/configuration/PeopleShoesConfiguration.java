package by.training.rest.configuration;

import by.training.rest.dao.brand.BrandJDBCTemplate;
import by.training.rest.dao.cart.CartJDBCTemplate;
import by.training.rest.dao.collectionShoes.CollectionShoesJDBCTemplate;
import by.training.rest.dao.color.ColorJDBCTemplate;
import by.training.rest.dao.colorShoes.ColorShoesJDBCTemplate;
import by.training.rest.dao.material.MaterialJDBCTemplate;
import by.training.rest.dao.menCollection.MenCollectionJDBCTemplate;
import by.training.rest.dao.shoes.ShoesJDBCTemplate;
import by.training.rest.dao.size.SizeJDBCTemplate;
import by.training.rest.dao.sizeShoes.SizeShoesJDBCTemplate;
import by.training.rest.dao.user.UserJDBCTemplate;
import by.training.rest.soap.PriceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "by.training.rest")
public class PeopleShoesConfiguration {

    @Bean(name = "menCollectionJDBCTemplate")
    public MenCollectionJDBCTemplate menCollectionJDBCTemplate(DataSource dataSource){
        MenCollectionJDBCTemplate menCollectionJDBCTemplate = new MenCollectionJDBCTemplate();
        menCollectionJDBCTemplate.setDataSource(dataSource);
        return menCollectionJDBCTemplate;
    }

    @Bean(name = "shoesJDBCTemplate")
    public ShoesJDBCTemplate shoesJDBCTemplate(DataSource dataSource){
        ShoesJDBCTemplate shoesJDBCTemplate = new ShoesJDBCTemplate();
        shoesJDBCTemplate.setDataSource(dataSource);
        return shoesJDBCTemplate;
    }

    @Bean(name = "materialJDBCTemplate")
    public MaterialJDBCTemplate materialJDBCTemplate(DataSource dataSource){
        MaterialJDBCTemplate materialJDBCTemplate = new MaterialJDBCTemplate();
        materialJDBCTemplate.setDataSource(dataSource);
        return materialJDBCTemplate;
    }

    @Bean(name = "brandJDBCTemplate")
    public BrandJDBCTemplate brandJDBCTemplate(DataSource dataSource){
        BrandJDBCTemplate brandJDBCTemplate = new BrandJDBCTemplate();
        brandJDBCTemplate.setDataSource(dataSource);
        return brandJDBCTemplate;
    }

    @Bean(name = "cartJDBCTemplate")
    public CartJDBCTemplate cartJDBCTemplate(DataSource dataSource){
        CartJDBCTemplate cartJDBCTemplate = new CartJDBCTemplate();
        cartJDBCTemplate.setDataSource(dataSource);
        return cartJDBCTemplate;
    }

    @Bean(name = "colorJDBCTemplate")
    public ColorJDBCTemplate colorJDBCTemplate(DataSource dataSource){
        ColorJDBCTemplate colorJDBCTemplate = new ColorJDBCTemplate();
        colorJDBCTemplate.setDataSource(dataSource);
        return colorJDBCTemplate;
    }

    @Bean(name = "sizeJDBCTemplate")
    public SizeJDBCTemplate sizeJDBCTemplate(DataSource dataSource){
        SizeJDBCTemplate sizeJDBCTemplate = new SizeJDBCTemplate();
        sizeJDBCTemplate.setDataSource(dataSource);
        return sizeJDBCTemplate;
    }

    @Bean(name = "colorShoesJDBCTemplate")
    public ColorShoesJDBCTemplate colorShoesJDBCTemplate(DataSource dataSource){
        ColorShoesJDBCTemplate colorShoesJDBCTemplate = new ColorShoesJDBCTemplate();
        colorShoesJDBCTemplate.setDataSource(dataSource);
        return colorShoesJDBCTemplate;
    }

    @Bean(name = "userJDBCTemplate")
    public UserJDBCTemplate userJDBCTemplate(DataSource dataSource){
        UserJDBCTemplate userJDBCTemplate = new UserJDBCTemplate();
        userJDBCTemplate.setDataSource(dataSource);
        return userJDBCTemplate;
    }

    @Bean(name = "collectionShoesJDBCTemplate")
    public CollectionShoesJDBCTemplate collectionShoesJDBCTemplate(DataSource dataSource){
        CollectionShoesJDBCTemplate collectionShoesJDBCTemplate = new CollectionShoesJDBCTemplate();
        collectionShoesJDBCTemplate.setDataSource(dataSource);
        return collectionShoesJDBCTemplate;
    }

    @Bean(name = "sizeShoesJDBCTemplate")
    public SizeShoesJDBCTemplate sizeShoesJDBCTemplate(DataSource dataSource){
        SizeShoesJDBCTemplate sizeShoesJDBCTemplate = new SizeShoesJDBCTemplate();
        sizeShoesJDBCTemplate.setDataSource(dataSource);
        return sizeShoesJDBCTemplate;
    }

    @Bean
    public DataSource dataSource(){
        DataSource dataSource = null;
        JndiTemplate jndiTemplate = new JndiTemplate();
        try {
            dataSource = jndiTemplate.lookup("jdbc/shoes",DataSource.class);
        }catch (NamingException e){

        }
        return dataSource;
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("by.training.rest.model.price");
        return marshaller;
    }

    @Bean
    public PriceClient priceClient(Jaxb2Marshaller marshaller) {
        PriceClient client = new PriceClient();
        client.setDefaultUri("http://localhost:8080/spring4soap-1/soap/Price.wsdl");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}