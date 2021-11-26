package com.example.sellhouse.model;

import androidx.annotation.DrawableRes;

public class Notification {
    public final String name;
    @DrawableRes
    public final int image;

    public Notification(final String name, @DrawableRes final int image) {
        this.name = name;
        this.image = image;
    }
}
