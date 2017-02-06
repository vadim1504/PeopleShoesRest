package by.training.rest.dao.user;

import by.training.rest.dao.AbstractDAO;
import by.training.rest.model.User;

import java.util.List;


public class UserJDBCTemplate extends AbstractDAO<User,String> {

    public void create(User entity) {
        jdbcTemplateObject.update(env.getProperty("createUser"), entity.getLogin(), entity.getPassword(),"1");
    }

    public User getEntity(String username) {
        User user = jdbcTemplateObject.queryForObject(env.getProperty("getUser"), new Object[]{username}, new UserMapper());
        return user;
    }

    public List<User> getListEntity() {
        List<User> users = jdbcTemplateObject.query(env.getProperty("getListUser"),new UserMapper());
        return users;
    }

    public void delete(String username) {
        jdbcTemplateObject.update(env.getProperty("deleteUser"), username);
    }

    public void update(String username, User entity) {
        jdbcTemplateObject.update(env.getProperty("updateUser"),entity.getLogin(),entity.getPassword(),entity.getEnabled(),username);
    }
}
