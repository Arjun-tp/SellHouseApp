package com.example.sellhouse.model;

import androidx.annotation.DrawableRes;

/**
 * Created by @mohamedebrahim96 on 27,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
public class House {
    public final String price;
    public final String address;
    public final int bedroom;
    public final int bathroom;
    @DrawableRes
    public final int image;

    public House(final String price, @DrawableRes final int image, String address, int bedroom, int bathroom) {
        this.price = price;
        this.image = image;
        this.bedroom = bedroom;
        this.address = address;
        this.bathroom = bathroom;
    }
}