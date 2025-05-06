package com.social.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;

    private String name;
    private String email;
    private String password;
    private List<UUID> followers;
    private List<UUID> following;

    @ManyToMany
    private List<PostModel> saved;

    public UserModel(){

    }

    public UserModel(String name, String email, String password, List<UUID> followers, List<UUID> following, List<PostModel> saved) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.followers = followers;
        this.following = following;
        this.saved = saved;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UUID> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UUID> followers) {
        this.followers = followers;
    }

    public List<UUID> getFollowing() {
        return following;
    }

    public void setFollowing(List<UUID> following) {
        this.following = following;
    }

    public List<PostModel> getSaved() {
        return saved;
    }

    public void setSaved(List<PostModel> saved) {
        this.saved = saved;
    }
}
