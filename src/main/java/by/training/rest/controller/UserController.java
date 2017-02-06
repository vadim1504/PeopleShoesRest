package by.training.rest.controller;

import by.training.rest.dao.user.UserJDBCTemplate;
import by.training.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Configuration
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    public UserJDBCTemplate userJDBCTemplate;

    @GetMapping(params = {"id"})
    public User getUser(@RequestParam(value = "username") String  username){
        return userJDBCTemplate.getEntity(username);
    }

    @GetMapping()
    public List<User> getListUser(){
        return userJDBCTemplate.getListEntity();
    }

    @PostMapping()
    public void createUser(@RequestBody User user){
        userJDBCTemplate.create(user);
    }

    @DeleteMapping(params = {"id"})
    public void deleteUser(@RequestParam(value = "username") String  username) {
        userJDBCTemplate.delete(username);
    }

    @PutMapping(params = {"id"})
    public void updateUser(@RequestBody User user, @RequestParam(value = "username") String username) {
        userJDBCTemplate.update(username,user);
    }
}
