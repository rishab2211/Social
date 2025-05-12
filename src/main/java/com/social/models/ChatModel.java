package com.social.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class ChatModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;

    private String chat_name;

    private String chat_image;

    @ManyToMany
    private List<UserModel> users = new ArrayList<>();

    private LocalDateTime timestamp;

    public ChatModel() {
    }

    public ChatModel(String chat_name, String chat_image, List<UserModel> users, LocalDateTime timestamp) {
        this.chat_name = chat_name;
        this.chat_image = chat_image;
        this.users = users;
        this.timestamp = timestamp;
    }

    public UUID getId() {
        return id;
    }

    public String getChat_name() {
        return chat_name;
    }

    public void setChat_name(String chat_name) {
        this.chat_name = chat_name;
    }

    public String getChat_image() {
        return chat_image;
    }

    public void setChat_image(String chat_image) {
        this.chat_image = chat_image;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
