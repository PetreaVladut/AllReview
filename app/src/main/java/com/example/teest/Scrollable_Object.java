package com.example.teest;

public class Scrollable_Object {
    private String name;
    private String imageResource;
    private int id;

    public Scrollable_Object(String name, String imageResource, int id) {
        this.name = name;
        this.imageResource = imageResource;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageResource() {
        return imageResource;
    }
    public int getId() {
        return id;
    }


    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }

    // other methods and properties
}