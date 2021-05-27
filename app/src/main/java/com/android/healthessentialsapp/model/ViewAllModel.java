package com.android.healthessentialsapp.model;

import java.io.Serializable;

public class ViewAllModel implements Serializable {

    String image, name, price, rating, description, link, num_reviews;

    public ViewAllModel() {
    }

    public ViewAllModel(String image, String name, String price, String rating, String description, String link, String num_reviews) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.link = link;
        this.num_reviews = num_reviews;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNum_reviews() {
        return num_reviews;
    }

    public void setNum_reviews(String num_reviews) {
        this.num_reviews = num_reviews;
    }
}
