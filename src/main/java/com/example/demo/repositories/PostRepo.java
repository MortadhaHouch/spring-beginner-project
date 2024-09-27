package com.example.demo.repositories;
import com.example.demo.models.*;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepo extends MongoRepository<Post, String> {
}
