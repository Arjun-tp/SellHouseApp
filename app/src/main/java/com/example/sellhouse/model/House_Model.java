package com.example.sellhouse.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;

public class House_Model  implements Parcelable {
    public final String price;
    public final String address;
    public final int bedroom;
    public final int bathroom;
    @DrawableRes
    public final int image;

    public House_Model(final String price, @DrawableRes final int image, String address, int bedroom, int bathroom) {
        this.price = price;
        this.image = image;
        this.address = address;
        this.bedroom = bedroom;
        this.bathroom = bathroom;
    }

    public House_Model(Parcel in){
        String[] data = new String[5];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        this.price = data[0];
        this.image = Integer.parseInt(data[1]);
        this.bedroom = Integer.parseInt(data[2]);
        this.address = data[3];
        this.bathroom = Integer.parseInt(data[4]);
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