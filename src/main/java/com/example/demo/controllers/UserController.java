package com.example.demo.controllers;
import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    UserService userService;
    UserController(){

    }
    @GetMapping("/")
    public String getRootMessage() {
        return "Hello World";
    }
    @GetMapping("/get")
    public List<User> getUsers() {
        return userService.findUsers();
    }
    @GetMapping("/get/{id}")
    public Optional<User> getUser(@PathVariable String id) {
        return userService.findUserById(id);
    }
    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
    @PutMapping("/edit/{id}")
    public User editUser(@RequestBody User user,@PathVariable String id) {
        try{
            return userService.updateUser(id,user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }
}
