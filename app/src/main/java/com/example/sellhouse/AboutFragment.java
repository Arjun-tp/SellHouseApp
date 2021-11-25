package com.example.sellhouse;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * Created by @mohamedebrahim96 on 25,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */


public class AboutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_sell_activity_one, container, false);
        View view = inflater.inflate(R.layout.about_fragment, container, false);
        getActivity().setTitle("About");

        view.findViewById(R.id.about_more_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/mohamedebrahim96"));
                startActivity(browserIntent);
            }
        });
        return view;
    }
}