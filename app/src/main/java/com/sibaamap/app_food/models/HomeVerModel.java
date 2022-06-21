package com.sibaamap.app_food.models;

import java.io.Serializable;

public class HomeVerModel {

    private String image;
    private String kind;
    private String name;
    private int price;
    private String rating;
    private String timing;


    public HomeVerModel(){

    }

    public HomeVerModel(String image, String kind, String name, int price, String rating, String timing) {
        this.image = image;
        this.kind = kind;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.timing = timing;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
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
}
