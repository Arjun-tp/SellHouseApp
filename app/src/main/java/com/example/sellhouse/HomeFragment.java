package com.example.sellhouse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

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