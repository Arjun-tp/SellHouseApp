package com.example.sellhouse.model;

import androidx.annotation.DrawableRes;

public class House_Model {
    public String price;
    public String address1;
    public String noOfBedrooms;
    public String noOfWashrooms;
    @DrawableRes
    public int image;

    public House_Model(final String price, @DrawableRes final int image, String address1, String noOfBedrooms, String noOfWashrooms) {
        this.price = price;
        this.image = image;
        this.noOfBedrooms = noOfBedrooms;
        this.address1 = address1;
        this.noOfWashrooms = noOfWashrooms;
    }

    public House_Model(){}

    public String getPrice() {
        return price;
    }

    public String getAddress1() {
        return address1;
    }

    public String getNoOfBedrooms() {
        return noOfBedrooms;
    }

    public String getNoOfWashrooms() {
        return noOfWashrooms;
    }

    public int getImage() {
        return image;
    }
}