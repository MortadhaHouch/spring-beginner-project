package com.example.demo.repositories;
import com.example.demo.models.*;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepo extends MongoRepository<Post, String> {
    List<Post> findByAuthor(String author);
    List<Post> findByTitle(String title);
    List<Post> findByAuthorAndTitle(String author, String title);
    Optional<Post> findByContent(String content);
    Optional<Post> findByContentAndTitle(String content,String title);
}
