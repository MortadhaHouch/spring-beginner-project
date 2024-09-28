package com.example.demo.services;
import com.example.demo.models.*;
import com.example.demo.repositories.PostRepo;
import com.example.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class PostService{

    @Autowired
    PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;

    public List<Post> findPosts(){
        return postRepo.findAll();
    }
    public List<Post> findPostsByAmount(String id,int amount){
        try{
            Optional<User> foundUser = userRepo.findById(id);
            if (foundUser.isPresent()){
                return postRepo.findAll().stream().skip(amount * 10L).limit(amount).toList();
            }else{
                return new ArrayList<>();
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Post> findPostsSortedByDate(String id){
        try{
            Optional<User> foundUser = userRepo.findById(id);
            ArrayList<Post> posts = new ArrayList<>();
            if (foundUser.isPresent()){
                foundUser.get().getPosts().stream().sorted((p1,p2)->{
                    Optional<Post> foundPost1 = postRepo.findById(p1);
                    Optional<Post> foundPost2 = postRepo.findById(p2);
                    if(foundPost1.isPresent() && foundPost2.isPresent()){
                        return foundPost1.get().getAddedOn().compareTo(foundPost2.get().getAddedOn());
                    }else{
                        return Integer.parseInt(null);
                    }
                }).forEach(p->{
                    Optional<Post> foundPost = postRepo.findById(p);
                    foundPost.ifPresent(posts::add);
                });
                return posts;
            }else{
                return new ArrayList<>();
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Post> findPostsByAmountSortedByDate(String id,int n){
        try{
            Optional<User> foundUser = userRepo.findById(id);
            ArrayList<Post> posts = new ArrayList<>();
            if (foundUser.isPresent()){
                foundUser.get().getPosts().stream().skip(n * 10L).limit(n).sorted((p1,p2)->{
                    Optional<Post> foundPost1 = postRepo.findById(p1);
                    Optional<Post> foundPost2 = postRepo.findById(p2);
                    if(foundPost1.isPresent() && foundPost2.isPresent()){
                        return foundPost1.get().getAddedOn().compareTo(foundPost2.get().getAddedOn());
                    }else{
                        return Integer.parseInt(null);
                    }
                }).forEach(p->{
                    Optional<Post> foundPost = postRepo.findById(p);
                    foundPost.ifPresent(posts::add);
                });
                return posts;
            }else{
                return new ArrayList<>();
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public Optional<Post> findPostById(String id){
        return postRepo.findById(id);
    }
    public Post updatePost(Post post){
        return postRepo.save(post);
    }
    public String deletePost(String id){
        postRepo.deleteById(id);
        return "Post deleted";
    }
    public String addPost(Post post){
        postRepo.insert(post);
        return "Post saved";
    }
    public User getUserByPost(String id){
        try{
            Optional<User> foundUser = userRepo.findById(id);
            return foundUser.orElse(null);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public ArrayList<User> getUsersByPost(List<String> posts){
        ArrayList<User> users = new ArrayList<>();
        posts.forEach(post -> {
            Optional<User> foundUser = userRepo.findById(post);
            foundUser.ifPresent(users::add);
        });
        return users;
    }
}
