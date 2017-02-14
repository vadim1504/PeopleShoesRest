package by.training.rest.configuration;

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