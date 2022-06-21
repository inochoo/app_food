package com.sibaamap.app_food.models;

public class DailymealModel {
    private String description;
    private String discount;
    private String image;
    private String type1;
    private String name;

    public DailymealModel(){

    }

    public DailymealModel(String description, String discount, String image, String type1, String name) {
        this.description = description;
        this.discount = discount;
        this.image = image;
        this.type1 = type1;
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
