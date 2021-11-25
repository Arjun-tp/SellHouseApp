package com.example.sellhouse.model;

import androidx.annotation.DrawableRes;

/**
 * Created by @mohamedebrahim96 on 25,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
public class Notification {
    public final String name;
    @DrawableRes
    public final int image;

    public Notification(final String name, @DrawableRes final int image) {
        this.name = name;
        this.image = image;
    }
}
