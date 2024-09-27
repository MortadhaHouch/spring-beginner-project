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
        return userService.findAll();
    }
    @PostMapping("/add")
    public List<User> addUser(@RequestBody User user) {
        userService.insert(user);
        return userService.findAll();
    }
    @PutMapping("/edit/{id}")
    public User editUser(@RequestBody User user,@PathVariable String id) {
        try{
            Optional<User> foundUser = userService.findById(id);
            if (foundUser.isPresent()) {
                userService.save(user);
                return user;
            }else{
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        try{
            Optional<User> foundUser = userService.findById(id);
            if (foundUser.isPresent()) {
                userService.delete(foundUser.get());
                return "user deleted";
            }else{
                return "user not found";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
