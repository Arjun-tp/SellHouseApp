package com.example.sellhouse.model;

import androidx.annotation.DrawableRes;

public class House_Model {
    public final String price;
    public final String address;
    public final int bedroom;
    public final int bathroom;
    @DrawableRes
    public final int image;

    public House_Model(final String price, @DrawableRes final int image, String address, int bedroom, int bathroom) {
        this.price = price;
        this.image = image;
        this.bedroom = bedroom;
        this.address = address;
        this.bathroom = bathroom;
    }
}