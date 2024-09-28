package com.example.demo.repositories;
import com.example.demo.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;
public interface UserRepo extends MongoRepository<User, String> {
    Optional<User> findUserByEmail(String email);
    List<User> findUsersByIsLoggedIn(boolean isLoggedIn);
    List<User> findByNameStartingWith(String name);
    List<User> findByName(String name);
    List<User> findByIsLoggedIn(Boolean loggedIn);
    List<User> findIsPrivate(Boolean isPrivate);
}