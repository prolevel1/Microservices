package com.UserServices.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "Users")
@Entity
public class User {
    @Id
    private  String userId;

    private String name;
    private String email;
    private String about;
    @Transient
  private List <Rating> ratingList = new ArrayList<>();

    public User() {
    }

    public User(String userId, String name, String email, String about, List<Rating> ratingList) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.about = about;
        this.ratingList = ratingList;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }


}
