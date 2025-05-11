package com.social.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class PostModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(nullable = false, updatable = false)
    private UUID id;

    private String caption;

    private  String imageUrl;
    private String videoUrl;

    @ManyToOne
    private UserModel user;

    @OneToMany
    private List<UserModel> liked;


    private LocalDateTime createdAt;

    @OneToMany
    private List<CommentModel> comments= new ArrayList<>();


    public PostModel(){

    }


    public PostModel(String caption, String imageUrl, String videoUrl, UserModel user, List<UserModel> liked, LocalDateTime createdAt, List<CommentModel> comments) {
        this.caption = caption;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.user = user;
        this.liked = liked;
        this.createdAt = createdAt;
        this.comments = comments;
    }

    public UUID getId() {
        return id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public List<UserModel> getLiked() {
        return liked;
    }

    public void setLiked(List<UserModel> liked) {
        this.liked = liked;
    }


    public List<CommentModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
    }
}
