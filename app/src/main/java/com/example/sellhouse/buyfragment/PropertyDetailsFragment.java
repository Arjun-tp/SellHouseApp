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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sellhouse.PropertyDetailsActivity;
import com.example.sellhouse.R;
import com.example.sellhouse.model.House_Model;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PropertyDetailsFragment extends Fragment {

    static androidx.appcompat.widget.Toolbar toolbarMovie;
    Activity activity;
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
//        title.setText(HouseData.address.toString());
//
//        getActivity().setTitle(movie.getTitle().toString());
//
//        release_date.setText(movie.getRelease_date().toString());
//        overview.setText(movie.getOverview().toString());
//        rating.setText(movie.getVote_average().toString() + "/");
//        Picasso.with(view.getContext()).load(movie.getImg()).into(poster);
//        Picasso.with(view.getContext()).load(movie.getBackdrop_path()).into(backdrop);
        return view;
    }

}