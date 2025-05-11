package com.social.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class ReelsModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(nullable = false, updatable = false)
    private UUID id;

    private String title;

    private String video;

    @ManyToOne
    private UserModel user;

    public ReelsModel() {
    }

    public ReelsModel(String video, UserModel user, String title) {
        this.video = video;
        this.user = user;
        this.title = title;
    }

    public UUID getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
