package com.sibaamap.app_food.models;

import java.io.Serializable;

public class ViewAllModel implements Serializable {
    private String description;
    private String image;
    private String name;
    private int price;
    private String rating;
    private String timing;
    private String type;

    public ViewAllModel(){

    }

    public ViewAllModel(String description, String image, String name, int price, String rating, String timing, String type) {
        this.description = description;
        this.image = image;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.timing = timing;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
