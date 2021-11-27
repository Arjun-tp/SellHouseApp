package com.example.sellhouse;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SellActivityTwo extends Fragment {
    @Nullable

    EditText propertyType, noOfBedrooms, noOfWashrooms, price, description;
    ImageView imageViewOne, imageViewTwo, imageViewThree;
    TextView dummy;
    Button post;
    public static String address1 = "";
    public static String address2 = "";
    public static String city = "";
    public static String province = "";
    public static String country = "";
    public static String postCode = "";

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sell_activity_two, container, false);
        propertyType = view.findViewById(R.id.extPropertyType);
        noOfBedrooms = view.findViewById(R.id.extNumberOfBedrooms);
        noOfWashrooms = view.findViewById(R.id.extNumberOfWashrooms);
        price = view.findViewById(R.id.extPrice);
        description = view.findViewById(R.id.extDescription);
        imageViewOne = view.findViewById(R.id.imageView11);
        imageViewTwo = view.findViewById(R.id.imageView22);
        imageViewThree = view.findViewById(R.id.imageView33);
        post = view.findViewById(R.id.btnPost);
        dummy = view.findViewById(R.id.txvDummy);

        Bundle bundle = this.getArguments();
        String address1Here = bundle.getString("address1");
        String address2Here = bundle.getString("address2");
        String cityHere = bundle.getString("city");
        String provinceHere = bundle.getString("province");
        String countryHere = bundle.getString("country");
        String postCodeHere = bundle.getString("postCode");
        Log.d("address1--------", address1Here);
        Log.d("address2Here--------", address2Here);
        Log.d("cityHere--------------", cityHere);
        Log.d("provinceHere-----------", provinceHere);
        Log.d("countryHere------------", countryHere);
        Log.d("postCodeHere----------", postCodeHere);

        return view;
    }

}
