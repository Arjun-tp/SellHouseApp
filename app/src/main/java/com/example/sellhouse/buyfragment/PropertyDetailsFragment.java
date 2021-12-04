package com.example.sellhouse.buyfragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sellhouse.PropertyDetailsActivity;
import com.example.sellhouse.R;
import com.example.sellhouse.RegisterActivity;
import com.example.sellhouse.model.House_Model;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class PropertyDetailsFragment extends Fragment {

    static androidx.appcompat.widget.Toolbar toolbarMovie;
    Activity activity;
    TextView overview;
    ImageView details_image,backdrop;
    public PropertyDetailsFragment() {

    }

    public static PropertyDetailsFragment newInstance(Activity act, androidx.appcompat.widget.Toolbar toolbar) {
        PropertyDetailsFragment fragment = new PropertyDetailsFragment();
        toolbarMovie = toolbar;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_house_details, container, false);
//        ButterKnife.bind(this, view);

        getActivity().setTitle("House Details");

        try {

            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        }catch(Exception e){

        }
        Intent intent = getActivity().getIntent();
        House_Model HouseData =(House_Model)intent.getParcelableExtra("houseDetails");
        TextView textView = view.findViewById(R.id.overview);
        Toast.makeText(getContext(),HouseData.address+"" , Toast.LENGTH_SHORT).show();
        textView.setText(HouseData.address);
        details_image = view.findViewById(R.id.poster);
        backdrop = view.findViewById(R.id.backdrop);

        Picasso.with(view.getContext()).load(HouseData.image).into(details_image);
        Picasso.with(view.getContext()).load(HouseData.image).into(backdrop);

        return view;
    }

}