package by.training.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@PropertySource(value = "classpath:storedProcedures.properties")
public abstract class AbstractDAO<E,K> {

    @Autowired
    public Environment env;

    private DataSource dataSource;
    public JdbcTemplate jdbcTemplateObject;
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public abstract void create(E entity);

    public abstract E getEntity(K id);

    public abstract List<E> getListEntity();

    public abstract void delete(K id);

    public abstract void update(K id, E entity);
}
