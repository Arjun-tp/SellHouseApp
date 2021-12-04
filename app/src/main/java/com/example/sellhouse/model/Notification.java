package com.example.sellhouse.model;

import androidx.annotation.DrawableRes;

public class Notification {
    public final String title;
    public final String description;
    @DrawableRes
    public final int image;

    public Notification(String title, String description, @DrawableRes final int image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }
}
