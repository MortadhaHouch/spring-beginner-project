package com.example.demo.services;
import com.example.demo.models.Post;
import com.example.demo.models.User;
import com.example.demo.repositories.PostRepo;
import com.example.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    PostRepo postRepo;
    public List<User> findUsers() {
        return userRepo.findAll();
    }
    public Optional<User> findUserById(String id) {
        return userRepo.findById(id);
    }
    public List<User> findUsersByAmount(int n){
        return userRepo.findAll().stream().skip(n * 10L).limit(n).toList();
    }
    public List<User> findLoggedInUsers(){
        return userRepo.findUsersByIsLoggedIn(true);
    }
    public User updateUser(String id,User user) {
        Optional<User> foundUserOptional = userRepo.findById(id);
        if (foundUserOptional.isPresent()) {
            User foundUser = foundUserOptional.get();
            foundUser.setName(user.getName());
            foundUser.setEmail(user.getEmail());
            foundUser.setAge(user.getAge());
            foundUser.setPassword(user.getPassword());
            return userRepo.save(foundUser);
        }else{
            return null;
        }
    }
    public User findUserByEmail(String email) {
        Query query = new Query();
        return mongoTemplate.find(query.addCriteria(Criteria.where("email").is(email)), User.class).get(0);
    }
    public List<Post> findPostsByUser(String id) {
        ArrayList<Post> posts = new ArrayList<>();
        try{
            Optional<User> foundUser = userRepo.findById(id);
            foundUser.ifPresent(user -> user.getPosts().forEach(post -> {
                Optional<Post> foundPost = postRepo.findById(post);
                foundPost.ifPresent(posts::add);
            }));
            return posts;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public String deleteUser(String id) {
        userRepo.deleteById(id);
        return "user deleted";
    }
    public User addUser(User user) {
        userRepo.insert(user);
        return user;
    }
}
