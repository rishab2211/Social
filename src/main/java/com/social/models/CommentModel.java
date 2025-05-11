package com.social.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class CommentModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;

    private String content;

    @ManyToOne
    private UserModel user;

    @ManyToMany
    private List<UserModel> liked = new ArrayList<>();

    private LocalDateTime createdAt;

    public CommentModel() {
    }

    public CommentModel( String content, UserModel user, List<UserModel> liked, LocalDateTime createdAt) {

        this.content = content;
        this.user = user;
        this.liked = liked;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<UserModel> getLiked() {
        return liked;
    }

    public void setLiked(List<UserModel> liked) {
        this.liked = liked;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
