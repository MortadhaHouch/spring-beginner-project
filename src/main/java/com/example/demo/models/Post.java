package com.example.demo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Post {
    @Id
    String id;
    String title;
    String content;
    String author;
    int likes;
    int dislikes;
    Date addedOn;
    Date updatedOn;
    public Post(String id, String title, String content,String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
