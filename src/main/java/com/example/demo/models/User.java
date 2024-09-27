package com.example.demo.models;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Document(collection = "user")
@Data
public class User {
    @Id ObjectId id;
    private String name;
    private int age;
    String email;
    String password;
    public User(String name, int age,String email,String password) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
    }
    public void setPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
    }
}