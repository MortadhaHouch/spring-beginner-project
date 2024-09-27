package com.example.demo.controllers;
import com.example.demo.models.*;
import com.example.demo.repositories.PostRepo;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {
    PostRepo postRepo;
    PostController(){

    }
    @GetMapping("/get")
    public List<Post> getPosts() {
        return postRepo.findAll();
    }
    @GetMapping("/get/{id}")
    public Optional<Post> getPost(@PathVariable String id) {
        return postRepo.findById(id);
    }
    @PostMapping("/add")
    public Post addPost(@RequestBody Post post) {
        postRepo.insert(post);
        return post;
    }
    @PutMapping("/edit/{id}")
    public Post editPost(@PathVariable String id, @RequestBody Post post) {
        try{
            Optional<Post> foundPost = postRepo.findById(id);
            if(foundPost.isPresent()){
                postRepo.save(post);
                return foundPost.get();
            }else{
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
