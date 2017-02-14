package by.training.rest.dao.user;

import by.training.rest.dao.AbstractDAO;
import by.training.rest.model.User;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserJDBCTemplate extends AbstractDAO<User,String> {

    public void create(User entity) {
        jdbcTemplateObject.update(env.getProperty("createUser"), entity.getUsername(), entity.getPassword(),"1");
    }

    public User getEntity(String username) {
        List<User> users = jdbcTemplateObject.query(env.getProperty("getUser"), new Object[]{username}, new UserMapper());
        if(users.isEmpty()){
            return null;
        }else{
            return users.get(0);
        }
    }

    public List<User> getListEntity() {
        List<User> users = jdbcTemplateObject.query(env.getProperty("getListUser"),new UserMapper());
        return users;
    }

    public void delete(String username) {
        jdbcTemplateObject.update(env.getProperty("deleteUser"), username);
    }

    public void update(String username, User entity) {
        jdbcTemplateObject.update(env.getProperty("updateUser"),entity.getUsername(),entity.getPassword(),entity.getEnabled(),username);
    }
}
