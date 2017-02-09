package by.training.rest.dao.user;

import by.training.rest.model.UserRoles;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRolesMapper implements RowMapper<UserRoles> {

    public UserRoles mapRow(ResultSet resultSet, int i) throws SQLException {
        UserRoles userroles = new UserRoles();
        userroles.setUsername(resultSet.getString("username"));
        userroles.setRole(resultSet.getString("role"));
        return userroles;
    }
}