package by.training.rest.dao.user;


import by.training.rest.dao.AbstractDAO;
import by.training.rest.model.UserRoles;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserRolesJDBCTemplate extends AbstractDAO<UserRoles,String> {

    public void create(UserRoles entity) {
        jdbcTemplateObject.update(env.getProperty("createUserRoles"), entity.getUsername(), entity.getRole());
    }

    public UserRoles getEntity(String UserRolesname) {
        List<UserRoles> userRoles = jdbcTemplateObject.query(env.getProperty("selectUserRoles"), new Object[]{UserRolesname}, new UserRolesMapper());
        if(userRoles.isEmpty()){
            return null;
        }else{
            return userRoles.get(0);
        }
    }

    public List<UserRoles> getListEntity() {
        return null;
    }

    public void delete(String UserRolesname) {
    }

    public void update(String UserRolesname, UserRoles entity) {
    }
}

