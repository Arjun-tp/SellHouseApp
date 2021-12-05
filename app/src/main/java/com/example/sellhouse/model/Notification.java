package com.example.sellhouse.model;

import androidx.annotation.DrawableRes;

public class Notification {
    public String title;
    public String description;
    @DrawableRes
    public int image;

    public Notification(String title, String description, @DrawableRes final int image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }
    public Notification(){};

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }
}
