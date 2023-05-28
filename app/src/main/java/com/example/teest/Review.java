package com.example.teest;

import java.io.Serializable;

public class Review implements Serializable {
    private int id;
    private String name;
    private String address;
    private String comment;
    private String category;
    private String author;
    private float rating;
    private String image;
    private static final long serialVersionUID = 1L;


    public Review(int id, String name, String address, String comment, String category, String author, float rating,String image) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.comment = comment;
        this.category = category;
        this.author = author;
        this.rating = rating;
        this.image=image;
    }

    public Review() {
        this.id = 0;
        this.name = null;
        this.address = null;
        this.comment = null;
        this.category = null;
        this.author = null;
        this.rating = 0;
        this.image=null;
    }

    // Getter and setter methods for each attribute

    public int getId() {
        return id;
    }
    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}

