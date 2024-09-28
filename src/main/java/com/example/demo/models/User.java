package com.example.demo.models;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@Document(collection = "user")
@Data
public class User {
    @Id ObjectId id;
    String name;
    int age;
    String email;
    String password;
    Boolean isLoggedIn;
    Boolean isPrivate;
    ArrayList<String> posts = new ArrayList<>();
    public User(String name, int age,String email,String password,Boolean isLoggedIn,Boolean isPrivate) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.isLoggedIn = isLoggedIn;
        this.isPrivate = isPrivate;
    }
    public void setPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
    }
}