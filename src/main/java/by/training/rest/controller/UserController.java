package by.training.rest.controller;

import by.training.rest.dao.user.UserJDBCTemplate;
import by.training.rest.dao.user.UserRolesJDBCTemplate;
import by.training.rest.model.User;
import by.training.rest.model.UserRoles;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Configuration
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    public UserJDBCTemplate userJDBCTemplate;
    @Autowired
    public UserRolesJDBCTemplate userRolesJDBCTemplate;
    private final Logger logger = Logger.getLogger(UserController.class);

    @GetMapping(params = {"username"},produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> getUser(@RequestParam(value = "username") String  username){
        logger.info("Fetching user with name="+username);
        User user = userJDBCTemplate.getEntity(username);
        if (user == null) {
            logger.warn("With user not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        UserRoles userRoles = userRolesJDBCTemplate.getEntity(username);
        if (userRoles == null) {
            return new ResponseEntity<User>(HttpStatus.CONFLICT);
        }
        user.setRole(userRoles);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getListUser(){
        logger.info("Fetching List user");
        List<User> users = userJDBCTemplate.getListEntity();
        if(users.isEmpty()){
            logger.warn("List user not found");
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> createUser(@RequestBody User user){
        logger.info("Create user");
        if (userJDBCTemplate.getEntity(user.getUsername())!=null) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        userJDBCTemplate.create(user);
        userRolesJDBCTemplate.create(new UserRoles(user.getUsername(),"ROLE_USER"));
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping(params = {"username"})
    public ResponseEntity<Void> deleteUser(@RequestParam(value = "username") String  username) {
        User user = userJDBCTemplate.getEntity(username);
        if (user == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        userJDBCTemplate.delete(username);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    public ResponseEntity<Void> updateUser(@RequestBody User user,Principal principal) {
        UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();
        User currentUser = userJDBCTemplate.getEntity(userDetails.getUsername());
        if (currentUser==null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());
        currentUser.setEnabled(user.getEnabled());
        userJDBCTemplate.update(user.getUsername(),currentUser);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }
}
