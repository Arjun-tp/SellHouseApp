package com.example.sellhouse;

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


public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        getActivity().setTitle("Home");
        view.findViewById(R.id.btnSellHouse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(BuyOrSellActivity.this, SellActivityOne.class);
                startActivity(intent);*/
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
//                        new SellActivityOne()).commit();
            }
        });

        return view;
    }
}