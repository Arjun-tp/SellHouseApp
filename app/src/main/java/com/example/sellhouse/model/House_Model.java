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
        this.bedroom = bedroom;
        this.address = address;
        this.bathroom = bathroom;
    }

    protected House_Model(Parcel in) {
        price = in.readString();
        address = in.readString();
        bedroom = in.readInt();
        bathroom = in.readInt();
        image = in.readInt();
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
        dest.writeStringArray(new String[] {this.price,
                String.valueOf(this.image),
                String.valueOf(this.bedroom),
        this.address,
                String.valueOf(this.bathroom)});
    }
}