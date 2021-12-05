package com.example.sellhouse.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;

public class House_Model implements Parcelable {
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

    public House_Model(Parcel in){
        String[] data = new String[5];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        this.price = data[0];
        this.image = Integer.parseInt(data[1]);
        this.noOfBedrooms = data[2];
        this.address1 = data[3];
        this.noOfWashrooms = data[4];
    }


    public static final Creator<House_Model> CREATOR = new Creator<House_Model>() {
        @Override
        public House_Model createFromParcel(Parcel in) {
            return new House_Model(in);
        }

        @Override
        public House_Model[] newArray(int size) {
            return new House_Model[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
                this.price,
                String.valueOf(this.image),
                String.valueOf(this.bedroom),
                String.valueOf(this.address),
                String.valueOf(this.bathroom)});
    }
}