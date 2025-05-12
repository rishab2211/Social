package com.social.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class MessageModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;

    private String Content;

    private String image;

    private LocalDateTime timestamp;

//    @JsonIgnore
    @ManyToOne
    private UserModel user;

    @ManyToOne
    private ChatModel chat;


    public MessageModel() {
    }

    public MessageModel(String content, String image, LocalDateTime timestamp, UserModel user, ChatModel chat) {

        Content = content;
        this.image = image;
        this.timestamp = timestamp;
        this.user = user;
        this.chat = chat;
    }

    public UUID getId() {
        return id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public ChatModel getChat() {
        return chat;
    }

    public void setChat(ChatModel chat) {
        this.chat = chat;
    }
}

